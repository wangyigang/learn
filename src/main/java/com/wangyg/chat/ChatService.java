package com.wangyg.chat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class ChatService {
    //selector 选择器
    private Selector selector;

    private ServerSocketChannel serverSocketChannel;

    private long timeout= 2000;

    public  ChatService(){

        try {
            //服务器channel
            serverSocketChannel = ServerSocketChannel.open();

            //选择器对象
            selector = Selector.open();

            //绑定端口
            serverSocketChannel.bind(new InetSocketAddress(9090));

            //设置非阻塞式
            serverSocketChannel.configureBlocking(false);

            //注册
            SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT); //监听连接
            System.out.println("服务器端准备就绪..");

            start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start() throws  Exception{
        int count=0;
        long start = System.nanoTime();

        while (true) {
            //监听客户端
            if (selector.select(timeout) == 0) {
                System.out.println(" 2 秒内没有连接我...");
                continue;
            }
            selector.select(timeout);

            long end = System.nanoTime();

            if (end - start >= TimeUnit.MILLISECONDS.toNanos(timeout)) {
                count=1;
            }else{
                count++;
            }

            if (count >= 10) {
                System.out.println(" 有可能发生空 轮询" + count + " 次");
                rebuildSelector();
                count=0;
                selector.selectNow();
                continue;
            }


            //得到SelectionKey对象，判断是事件
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()){
                SelectionKey selectionKey=iterator.next();
                if(selectionKey.isAcceptable()){     //连接事件
                    //获取网络通道
                    SocketChannel accept = serverSocketChannel.accept();
                    //设置非阻塞式
                    accept.configureBlocking(false);
                    //连接上了  注册读取事件
                    accept.register(selector,SelectionKey.OP_READ);
                    System.out.println(accept.getRemoteAddress().toString()+"上线了");
                }
                if(selectionKey.isReadable()){     //读取客户端数据事件
                    //读取客户端发来的数据
                    readClientData(selectionKey);
                }
                //手动从当前集合将本次运行完的对象删除
                iterator.remove();
            }
        }

    }

    private void rebuildSelector() throws IOException {
        Selector newSelector=Selector.open();
        Selector oldSelect=selector;
        for (SelectionKey selectionKey : oldSelect.keys()) {
            int i = selectionKey.interestOps();
            selectionKey.cancel();
            selectionKey.channel().register(newSelector,i);
        }
        selector=newSelector;
        oldSelect.close();
    }

    //读取客户端发来的数据
    private void readClientData(SelectionKey selectionKey) throws IOException {
        System.out.println("aaaaaaaaaaaa");
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        int read = socketChannel.read(byteBuffer);
        byteBuffer.flip();
        if(read>0){
            byte[] bytes=new byte[read];
            byteBuffer.get(bytes,0,read);
            //读取了数据  广播
            String s = new String(bytes,"utf-8");
//            writeClientData(socketChannel,s);
        }
    }


}
