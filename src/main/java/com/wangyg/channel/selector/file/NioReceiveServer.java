package com.wangyg.channel.selector.file;


import com.wangyg.channel.NioDemoConfig;
import com.wangyg.netty.basic.util.IOUtil;
import com.wangyg.netty.basic.util.Logger;
import com.wangyg.netty.basic.util.Print;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class NioReceiveServer {
    //编码即
    private Charset charset = Charset.forName("UTF-8");
    /**
     * 内部类
     */
    static class Client{
        //文件名称
        String fileName;
        //长度
        long  fileLength;
        //开始传输的时间
        long startTime;
        //客户端的地址
        InetSocketAddress remoteAddress;
        //输出的文件通道
        FileChannel outChannel;
    }

    private ByteBuffer buffer = ByteBuffer.allocate(1024);

    //使用map保存每个文件传输，当op_read可读时，根据通道找到对应的对象
    Map<SelectableChannel, Client> clientMap = new HashMap<>();

    public void startserver() throws IOException{
        //获取选择器
        Selector selector = Selector.open();
        //获取通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //获取serverSocket
        ServerSocket socket = serverSocketChannel.socket();
        //设置为非阻塞
        serverSocketChannel.configureBlocking(false);//非阻塞
        //4.进行绑定
        InetSocketAddress address = new InetSocketAddress(NioDemoConfig.SOCKET_SERVER_PORT);
        //进行绑定
        socket.bind(address);
        //5.将通道注册到选择器上，并注册的IO事件 为接收新连接
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT); //将时间注册为SelectionKey.op_accept
        Print.tcfo("serverchannel is listening...");  //线程名+类名+方法名进行输出

        //6.选择感兴趣的IO就绪事件
        while (selector.select() > 0) {
            //7. 获取选择键集合
            Iterator<SelectionKey> it = selector.selectedKeys().iterator();
            while (it.hasNext()) {
                //8.获取当个选择键，并处理
                SelectionKey key = it.next();
                //接下来进行判断key 具体是什么类型事件，然后进行对应处理
                if (key.isAcceptable()) {
                    //判断是否是新连接事件,
                    ServerSocketChannel server = (ServerSocketChannel) key.channel();
                    SocketChannel socketChannel = server.accept();
                    //防御式编程，也挺好
                    if(socketChannel == null) continue;

                    //将新连接设置为非阻塞
                    socketChannel.configureBlocking(false);
                    //12.将很独断新连接通道注册到选择器上
                    SelectionKey selectionKey = socketChannel.register(selector, SelectionKey.OP_READ);
                    //为每一条传输通道，建立一个Client客户端对象，放入map,供后面使用
                    Client client = new Client(); //静态内部类的client
                    //获取远端地址的IP
                    client.remoteAddress= (InetSocketAddress) socketChannel.getRemoteAddress();
                    //将数据放入到map中
                    clientMap.put(socketChannel, client);
                    Logger.info(socketChannel.getRemoteAddress()+"连接成功");
                } else if (key.isReadable()) {
                    //13.若接收的事件是数据可读事件， 就读取客户端新练级
                    processData(key);
                }
                //NIo的特点只会累加，一选择的键的集合不会删除
                //如果不删除，下一次又会被select函数选中
                it.remove();
            }
        }

    }

    /**
     * 处理客户端传输过来的数据
     * @param key
     */
    private void processData(SelectionKey key) {
        Client client = clientMap.get(key.channel());
        SocketChannel channel = (SocketChannel) (key.channel());
        int num=0;
        try {
            buffer.clear(); //进行清理
            while ((num= channel.read(buffer))>0){
                buffer.flip();//进行反转
                if (null == client.fileName) {
                    //客户端发过来的，首先是文件名
                    //根据文件名，创建服务器端的文件，将文件通道保存到客户端
                    String fileName = charset.decode(buffer).toString(); //进行解码
                    String destPath = IOUtil.getResourcePath(NioDemoConfig.SOCKET_RECEIVE_PATH);
                    File direcotry = new File(destPath);
                    if (!direcotry.exists()) {
                        direcotry.mkdir();// 如果不存在，就创建目录
                    }
                    client.fileName =fileName;
                    //获取绝对路径+
                    String fullName = direcotry.getAbsolutePath()+ File.separatorChar+fileName;
                    Logger.info("NIO 传输目标文件:" + fullName);

                    File file = new File(fullName);

                    FileChannel fileChannel = new FileOutputStream(file).getChannel(); //获取channel
                    client.outChannel = fileChannel;
                } else if (0 == client.fileLength) {
                    //客户端发送过来的，齐次是文件长度
                    long fileLength = buffer.getLong();
                    client.fileLength = fileLength;
                    client.startTime = System.currentTimeMillis();

                    Logger.info("NIO 文件传输开始:");

                }else{
                    //客户端发送过来的，最后是文件内容，写入文件内容
                    client.outChannel.write(buffer);
                }
                buffer.clear();
            }
            key.cancel();// 取消key
        } catch (IOException e) {
            e.printStackTrace();
            key.cancel();
            return ;
        }
        //读取数量-1，表示客户端传输标志到了
        if (num == -1) {
            IOUtil.closeQuietly(client.outChannel);
            System.out.println("上传完毕");
            key.cancel();
            Logger.info("文件接收成功, File Name:"+ client.fileName);
            Logger.info("Size:" + IOUtil.getFormatFileSize(client.fileLength));

            long endTime = System.currentTimeMillis();
            Logger.info("Nio io传输毫秒数: " + (endTime - client.startTime));
        }
    }

    public static void main(String[] args) throws IOException {
        NioReceiveServer server = new NioReceiveServer();
        server.startserver();
    }
}
