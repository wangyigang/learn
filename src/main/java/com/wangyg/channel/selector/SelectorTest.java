package com.wangyg.channel.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

public class SelectorTest {

    public static void main(String[] args) throws IOException {

        //调用静态方法open()获取Selecotr实例
        Selector selector = Selector.open();
       //第二步：将通道注册到选择器实例中

        //获取通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //设置为非阻塞
        serverSocketChannel.configureBlocking(false);//设置为非阻塞
        //绑定连接
        serverSocketChannel.bind(new InetSocketAddress(8888));//绑定端口
        //5.将通道注册到选择器上，并制定监听事件为接受连接 事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);//接收连接事件

        //轮询，选择感兴趣的IO就绪事件-选择键集合
        while (selector.select() > 0) {
            Set selectedKeys = selector.selectedKeys();
            Iterator keyIterator = selectedKeys.iterator();
            while (keyIterator.hasNext()) {
                SelectionKey key = (SelectionKey) keyIterator.next();
                if (key.isAcceptable()) { //判断是否是 有新连接

                } else if (key.isConnectable()) {
                    //IO事件：传输通道连接成功
                } else if (key.isReadable()) {
                    //IO事件：传输通道可读
                } else if (key.isWritable()) {
                    //IO事件：传输通道可写
                }

                //处理完成后，移除选择器
                keyIterator.remove();
            }
        }
    }
}
