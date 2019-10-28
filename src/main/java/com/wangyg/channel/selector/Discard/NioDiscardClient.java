package com.wangyg.channel.selector.Discard;

import util.Logger;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NioDiscardClient {
    public static void startClient() throws IOException{
        //address--socketchannel 通过address进行连接服务器
        InetSocketAddress address =
                new InetSocketAddress("127.0.0.1",8888);

        //获取通道
        SocketChannel socketChannel = SocketChannel.open(address);//获取通道
        //切换为非阻塞模式
        socketChannel.configureBlocking(false);
        //不断的自旋，等待链接完成，或者做一些其他事情
        while (!socketChannel.finishConnect()) {

        }
        Logger.info("客户端连接成功...");
        //分配指定大小的缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put("hello world".getBytes());
        byteBuffer.flip();//进行翻转
        //发送到服务器端
        socketChannel.write(byteBuffer);
        socketChannel.shutdownOutput();
        socketChannel.close(); //关闭连接
        Logger.info("客户端关闭");
    }

    public static void main(String[] args) throws IOException {
        startClient();
    }
}
