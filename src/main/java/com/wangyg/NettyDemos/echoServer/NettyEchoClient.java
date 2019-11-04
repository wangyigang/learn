package com.wangyg.NettyDemos.echoServer;

import com.wangyg.netty.basic.NettyDemoConfig;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import util.Dateutil;
import util.Logger;
import util.Print;

import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class NettyEchoClient {
    private int serverPort;
    private String serverIp;

    Bootstrap b = new Bootstrap();

    /**
     * 构造函数
     *
     * @param ip
     * @param port
     */
    public NettyEchoClient(String ip, int port) {
        this.serverIp = ip;
        this.serverPort = port;
    }

    public void runClient() {
        //创建reactor线程组
        EventLoopGroup workerLoopGroup = new NioEventLoopGroup();

        try {
            //设置reactor线程组
            b.group(workerLoopGroup);
            //设置Nio类型的channel
            b.channel(NioSocketChannel.class);
            //设置监听端口
            b.remoteAddress(serverIp, serverPort);
            //设置通道的参数
            b.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);

            //装配子通道流水线
            b.handler(new ChannelInitializer<SocketChannel>() {
                //有链接到达时会创建一个channel
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    //pipeline管理子通道channel中的handler
                    //向子channel流水线添加一个handler处理器
                    ch.pipeline().addLast(NettyEchoClientHandler.INSTANCE);
                }
            });
            ChannelFuture f = b.connect();
            //添加监听器
            f.addListener((ChannelFuture futureListener) -> {
                if (futureListener.isSuccess()) {
                    Logger.info("EchoClient客户端连接成功!");

                } else {
                    Logger.info("EchoClient客户端连接失败!");
                }
            });

            // 阻塞,直到连接完成
            f.sync();
            Channel channel = f.channel();

            Scanner scanner = new Scanner(System.in);
            Print.tcfo("请输入发送内容:");

            while (scanner.hasNext()) {
                //获取输入的内容
                String next = scanner.next();
                byte[] bytes = (Dateutil.getNow() + " >>" + next).getBytes("UTF-8");
                //发送ByteBuf
                ByteBuf buffer = channel.alloc().buffer();
                buffer.writeBytes(bytes);
                channel.writeAndFlush(buffer);
                Print.tcfo("请输入发送内容:");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 优雅关闭EventLoopGroup，
            // 释放掉所有资源包括创建的线程
            workerLoopGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        int port = NettyDemoConfig.SOCKET_SERVER_PORT;
        String ip = NettyDemoConfig.SOCKET_SERVER_IP;
        new NettyEchoClient(ip, port).runClient();
    }
}
