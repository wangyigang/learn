package com.wangyg.netty.basic;

import com.wangyg.netty.basic.util.Logger;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;


public class NettyDiscardServer {
    //成员变量,服务端port 端口
    private final int serverPort;

    //serverbootstrap 服务启动类，他的职责是一个组装和集成器，将不同的netty组件组装在一起
    ServerBootstrap b = new ServerBootstrap();

    //构造函数
    public NettyDiscardServer(int port) {
        this.serverPort = port;
    }

    public void runServer() {
        //创建反应器线程
        //包工头，负责服务器端通信新连接的IO事件的监听
        EventLoopGroup bossLoopGroup = new NioEventLoopGroup(1); //主线程只生成一个线程
        //第二个 工人-主要负责传输通道的IO事件的处理
        EventLoopGroup workerLoopGroup = new NioEventLoopGroup();

        //设置反应器线程组
        b.group(bossLoopGroup, workerLoopGroup); //设置reactor线程组
        b.channel(NioServerSocketChannel.class); //设置NIO类型的channel

        //设置监听的端口
        b.localAddress(serverPort);
        //设置通道的参数
        b.option(ChannelOption.SO_KEEPALIVE, true); //设置长连接
        b.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT); //内存池

        b.childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);

        //5.装配子通道流水线
        b.childHandler(new ChannelInitializer<SocketChannel>() {
            //有连接到达时， 就会创建一个channel
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(new NettyDiscardHandler()); //handler处理器，handler处理器的组哟用就是对应到IO时间，实现io时间的业务处理，
            }
        });

        //绑定服务器端
        //通过sync听并不方法阻塞知道绑定成功
        try {
            ChannelFuture channelFuture = b.bind().sync();
            Logger.info(" 服务器启动成功，监听端口: " +
                    channelFuture.channel().localAddress());

            // 7 等待通道关闭的异步任务结束
            // 服务监听通道会一直等待通道关闭的异步任务结束
            ChannelFuture closeFuture = channelFuture.channel().closeFuture();
            closeFuture.sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            //进行优雅关闭 --释放所有资源包括创建线程的线程
            workerLoopGroup.shutdownGracefully();
            bossLoopGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        int port = 8080;
        new NettyDiscardServer(port).runServer();
    }

}
