package com.wangyg.NettyDemos.NettyDumpSend;

import com.wangyg.netty.basic.NettyDemoConfig;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.buffer.PooledByteBufAllocator;
import util.Logger;

import java.nio.charset.Charset;

public class NettyDumpSendClient {
    private  int serverPort;
    private String serverIp;

    Bootstrap b = new Bootstrap();

    /**
     * 构造函数
     * @param serverIp
     * @param serverPort
     */
    public NettyDumpSendClient(String serverIp, int serverPort ) {
        this.serverPort = serverPort;
        this.serverIp = serverIp;
    }
    public void runCLient(){
        //创建反应器线程组
        EventLoopGroup workerLoopGroup = new NioEventLoopGroup();
        try {
            //设置reactor线程组
            b.group(workerLoopGroup);
            //设置nio类型的channel
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
                    //pipeLinde管理子通道channel中的handler
                    //向子channel 流水线添加一个Handler处理器
                    ch.pipeline().addLast(NettyEchoClientHandler.INSTANCE);
                }
            });
            ChannelFuture f = b.connect();
            f.addListener((ChannelFuture future) ->{
                if (future.isSuccess()) {
                    Logger.info("Echo client 客户端连接成功!");
                } else{
                    Logger.info("Echo Client 客户端连接失败! ");
                }
            });

            //阻塞，直到连接完成
            f.sync();
            Channel channel = f.channel();

            byte[] bytes = "疯狂创客圈：高性能学习社群!".getBytes(Charset.forName("utf-8"));
            for (int i = 0; i < 1000; i++) {
                //发送ByteBuf
                ByteBuf buffer = channel.alloc().buffer();
                buffer.writeBytes(bytes);
                channel.writeAndFlush(buffer);
            }


            // 7 等待通道关闭的异步任务结束
            // 服务监听通道会一直等待通道关闭的异步任务结束
            ChannelFuture closeFuture =channel.closeFuture();
            closeFuture.sync();


        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //优雅关闭
            workerLoopGroup.shutdownGracefully(); //进行有噶释放所有资源包括创建的线程
        }
    }

    /**
     * 入口程序
     * @param args
     */
    public static void main(String[] args) {
        int port  = NettyDemoConfig.SOCKET_SERVER_PORT;
        String ip = NettyDemoConfig.SOCKET_SERVER_IP;

        new NettyDumpSendClient(ip, port).runCLient();
    }

}
