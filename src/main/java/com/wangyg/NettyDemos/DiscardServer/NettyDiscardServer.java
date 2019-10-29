package com.wangyg.NettyDemos.DiscardServer;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoop;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

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
     */
    public  void runServer(){
        //创建反应器线程组
        EventLoopGroup bossLoopGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerLoopGroup = new NioEventLoopGroup();

        try {
            //设置reactor线程组
            b.group(bossLoopGroup, workerLoopGroup);
            //设置Nio类型的channel
            b.channel(NioServerSocketChannel.class);
            //设置监听端口
            b.localAddress(serverPort);

            //设置通道的参数
            b.option(ChannelOption.SO_KEEPALIVE, true);  //设置为长连接
            b.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT); //使用PolledByteBufAllocator为默认的内存分配

            //装配子通道流水线
            b.childHandler(new ChannelInitializer<SocketChannel>() {
                //有连接到达时会创建一个channel
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {

                }
            });

        }finally {

        }
    }
}
