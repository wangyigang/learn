package com.wangyg.NettyDemos.DiscardServer;

import com.wangyg.netty.basic.NettyDemoConfig;
import com.wangyg.netty.basic.NettyDiscardHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import util.Logger;

/**
 * 添加依赖netty-all
 * 4.1.6.Final
 */
public class NettyDiscardServer {
    private final int serverPort;
    ServerBootstrap b = new ServerBootstrap();

    /**
     * 构造函数
     * @param serverPort
     */
    public NettyDiscardServer(int serverPort) {
        this.serverPort = serverPort;
    }

    /**
     * runserver
     * EventLoopGroup 是一个Reactor反应器，反应器的作用是进行一个IO事件的select查询和dispatch分发
     * Netty中对应的反应器组件有多种，应用场景不同，用到的反应器也各不相同，对于多线程的Java Nio通信场景
     * Netty 的反应器类型为NioEventLoopGroup
     *  代码中有两个NioEventLoopGroup: 第一个通常被称为包工头， 负责服务器通道新连接的IO事件 的监听
     *  第二个通常被称为工人， 主要负责传输通道  的 IO事件的处理
     *
     *  Handler处理器（也称为处理程序） 处理程序： 实现IO事件的业务处理
     *
     *
     *  ServerBootstrap: 它的职责是组装和集成器 ,  将各种各样的Netty组件 组装在一起，实现Netty服务器的监听和启动
     */
    public  void runServer(){
        //创建反应器线程组
        EventLoopGroup bossLoopGroup = new NioEventLoopGroup(1);  //反应器
        EventLoopGroup workerLoopGroup = new NioEventLoopGroup();

        try {
            //设置reactor线程组
            b.group(bossLoopGroup, workerLoopGroup);
            //设置Nio类型的channel
            b.channel(NioServerSocketChannel.class); //设置Nio类型的channel
            //设置监听端口
            b.localAddress(serverPort);

            //设置通道的参数
            b.option(ChannelOption.SO_KEEPALIVE, true);  //设置为长连接
            b.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT); //使用PolledByteBufAllocator为默认的内存分配

            //装配子通道流水线
            b.childHandler(new ChannelInitializer<SocketChannel>() {
                //有连接到达时会创建一个channel
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new NettyDiscardHandler());
                }
            });
            //绑定server
            ChannelFuture channelFuture = b.bind().sync();
            Logger.info("服务器启动成功，监听端口:" + channelFuture.channel().localAddress());
            //等待通道关闭的异步任务结束
            //服务监听通道会一直等待通道关闭的异步任务结束
            ChannelFuture closeFuture = channelFuture.channel().closeFuture();

            closeFuture.sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //优雅关闭EventLoopGroup
            workerLoopGroup.shutdownGracefully();
            bossLoopGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        int port = NettyDemoConfig.SOCKET_SERVER_PORT;
        Logger.info(port);
        new NettyDiscardServer(port).runServer();
    }
}
