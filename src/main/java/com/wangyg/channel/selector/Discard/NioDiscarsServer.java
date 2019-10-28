package com.wangyg.channel.selector.Discard;

import util.Logger;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

public class NioDiscarsServer {
    public static void startServer() throws IOException {
        //获取选择器
        Selector selector = Selector.open();
        //获取通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //设置为非阻塞
        serverSocketChannel.configureBlocking(false);
        //绑定连接
        serverSocketChannel.bind(new InetSocketAddress(8888));
        Logger.info("服务器启动成功");
        //5.将通道注册的 接收新连接 IO事件 注册到选择器上
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        //6.轮询感兴趣的IO就绪事件 （选择键集合）
        while (selector.select() > 0) {
            //获取选择键集合
            Iterator<SelectionKey> selectedKeys = selector.selectedKeys().iterator();

            //遍历
            while (selectedKeys.hasNext()) {
                //获取单个选择器，并处理
                SelectionKey selectionKey = selectedKeys.next();
                //判断key具体是什么事件
                if (selectionKey.isAcceptable()) {
                    //若选择事件是连接  就绪事件，就获取客户端连接
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    //切换为非阻塞模式
                    socketChannel.configureBlocking(false);
                    //将新连接的通道的可读时间，注册到选择器上
                    socketChannel.register(selector, SelectionKey.OP_READ);
                } else if (selectionKey.isReadable()) {
                    //如果选择键 IO事件是可读事件，读取数据

                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel(); //强转为socketchannel
                    //读取数据，然后丢弃
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    int length =0;
                    while ((length = socketChannel.read(byteBuffer)) > 0) {
                        //进行模式转换
                        byteBuffer.flip();
                        Logger.info(new String(byteBuffer.array(), 0, length));
                        byteBuffer.clear();//清空数据内容
                    }
                    socketChannel.close();//关闭通道
                }
                //移除选择键
                selectedKeys.remove();
            }
        }
        //关闭连接
        serverSocketChannel.close();
    }

    public static void main(String[] args) throws IOException {
        startServer();
    }
}
