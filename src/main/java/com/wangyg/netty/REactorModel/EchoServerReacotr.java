package com.wangyg.netty.REactorModel;

import com.wangyg.channel.NioDemoConfig;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * 反应器
 */
public class EchoServerReacotr implements Runnable {
    Selector selector; //构造函数

    ServerSocketChannel serverSocket; //服务端server

    //构造器


    public EchoServerReacotr() throws IOException {
        this.selector = Selector.open();
        this.serverSocket = ServerSocketChannel.open();
        InetSocketAddress address = new InetSocketAddress(NioDemoConfig.SOCKET_SERVER_IP, NioDemoConfig.SOCKET_SERVER_PORT);
        //进行绑定
        serverSocket.socket().bind(address);
        //设置为非阻塞
        serverSocket.configureBlocking(false);

        //分布处理，第一步，接收accept事件
        SelectionKey sk = serverSocket.register(selector, SelectionKey.OP_ACCEPT);

        //attach
        sk.attach(new AcceptorHandler());
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                selector.select();
                Set<SelectionKey> selected = selector.selectedKeys();
                Iterator<SelectionKey> it = selected.iterator();
                while (it.hasNext()) {
                    //Reactor负责dispatch收到的事件
                    SelectionKey sk = it.next();
                    dispatch(sk);
                }
                selected.clear();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    //进行分发，处理
    void dispatch(SelectionKey sk) {
        Runnable handler = (Runnable) sk.attachment();
        //调用之前attach绑定到选择键的handler处理器对象
        if (handler != null) {
            handler.run();
        }
    }

    /**
     * 内部类，注册ServerSocket服务监听连接的接受事件之后，创建一个AcceptorHandler新连接处理器的实例
     * 作为附件，被设置attcch到了SelectionKey中
     *
     */
    private class AcceptorHandler implements Runnable {
        //run接口
        @Override
        public void run() {
            try {
                SocketChannel channel = serverSocket.accept();
                if (channel != null)
                    new EchoHandler(selector, channel);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) throws IOException {
        new Thread(new EchoServerReacotr()).start();
    }
}
