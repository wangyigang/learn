package com.wangyg.netty.MutltiReactor;

import com.wangyg.channel.NioDemoConfig;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class MultiThreadEchoServerReactor {
    ServerSocketChannel serverSocket;

    AtomicInteger next = new AtomicInteger(0);//默认值为0

    Selector[] selectors = new Selector[2];

    //引入多个子反应器
    SubReactor[] subReactors = null;

    public MultiThreadEchoServerReactor() throws IOException {
        //初始化多个选择器
        selectors[0] = Selector.open();
        selectors[1] = Selector.open();
        //创建serversocket
        serverSocket = ServerSocketChannel.open();
        //使用ip,端口号，创建address
        InetSocketAddress address = new InetSocketAddress(NioDemoConfig.SOCKET_SERVER_IP, NioDemoConfig.SOCKET_SERVER_PORT); //ip,端口号
        //将address绑定到socket上面
        serverSocket.socket().bind(address);

        //设置为非阻塞
        serverSocket.configureBlocking(false);
        //第一个选择器，负责监控新连接事件
        SelectionKey sk = serverSocket.register(selectors[0], SelectionKey.OP_ACCEPT); //负责监听新连接事件
        //绑定Handler：attach新连接监控handler处理器到SelectionKey（选择器）
        sk.attach(new AcceptorHandler());
        //第一个子反应器，一子反应器负责一个选择器
        SubReactor subReactor1 = new SubReactor(selectors[0]);
        //第二个反应器，一子反应器负责一个选择器
        SubReactor subReactor2 = new SubReactor(selectors[1]);
        //进行构造子反应器数组
        subReactors = new SubReactor[]{subReactor1, subReactor2};
    }

    /**
     * 进行开启服务
     */
    private void startService() {
        new Thread(subReactors[0]).start();
        new Thread(subReactors[1]).start();
    }

    //反应器
    class SubReactor implements Runnable {
        //每条线程负责一个选择器的查询
        final Selector selector;

        //使用构造器进行初始化
        public SubReactor(Selector selector) {
            this.selector = selector;
        }

        /**
         * 反应器的主要逻辑
         */
        @Override
        public void run() {
            try {
                while (!Thread.interrupted()) { //判断当前线程是否被interrut 被打断
                    selector.select(); //选择 为通道准备好IO事件的
                    Set<SelectionKey> keySet = selector.selectedKeys();

                    Iterator<SelectionKey> it = keySet.iterator();

                    while (it.hasNext()) {
                        //反应器负责dispatch收到的事件
                        SelectionKey sk = it.next();
                        dispatch(sk); //收到的事件调用dispatch进行分发事件，进行处理
                    }
                    keySet.clear();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        void dispatch(SelectionKey sk) {
            Runnable handler = (Runnable) sk.attachment(); //检索当前附件，收到当前事件的任务
            //调用之前attach绑定到选择键的handler处理器对象
            if (handler != null) {
                handler.run(); //是一个线程，进行执行任务
            }
        }
    }

    /**
     * Handler:新连接处理器
     */
    class AcceptorHandler implements Runnable {

        @Override
        public void run() {
            try {
                SocketChannel channel = serverSocket.accept();
                if(channel!= null){
                    new MultiThreadEchoHandler(selectors[next.get()], channel);
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        MultiThreadEchoServerReactor server =
                new MultiThreadEchoServerReactor();
        server.startService();
    }
}
