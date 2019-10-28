package com.wangyg.RPC;

import java.io.UnsupportedEncodingException;
import java.util.List;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.ServerChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.ByteToMessageDecoder;

/**
 * Created by wangyg-b
 */
public class RPCServer {

    /**
     * 客户端请求处理接口
     * 接口实现类应尽可能自行处理各种正常或异常情况
     * 并将各种处理的结果封装到响应报文返回给客户端
     */
    public static interface Handler {

        /**
         * @param input 请求报文
         * @return 响应报文
         */
        byte[] handle(byte[] input);

    }

    private Handler handler;
    private String host;
    private int port;

    /**
     * @param host    服务端要绑定到主机
     * @param port    服务端要绑定的端口
     * @param handler 负责处理请求的接口实现类
     */
    public RPCServer(String host, int port, Handler handler) {
        this.host = host;
        this.port = port;
        this.handler = handler;
    }

    public void start() throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        Class<? extends ServerChannel> channelClass =
                NioServerSocketChannel.class;
        ServerBootstrap b = new ServerBootstrap();
        b.group(bossGroup, workerGroup).channel(channelClass)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new VaryLengthMessageDecoder())
                                .addLast(new DefaultHandler());
                    }
                }).option(ChannelOption.SO_BACKLOG, 128)
                .childOption(ChannelOption.SO_KEEPALIVE, true);
        b.bind(host, port).sync();
    }

    private class VaryLengthMessageDecoder  extends  ByteToMessageDecoder {

        @Override
        protected void decode(
                ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
            int n = in.readableBytes();
            if (n < 4)
                return;
            int len = in.getInt(in.readerIndex());
            if (len < 0 || len > 8 * 1024 * 1024) {
                throw new RuntimeException(
                        "invalid message length: " + len);
            }
            if (n < 4 + len)
                return;
            in.skipBytes(4);
            out.add(in.readBytes(len));
        }

        @Override
        public void exceptionCaught(
                ChannelHandlerContext ctx, Throwable cause) {
            cause.printStackTrace();
            ctx.close();
        }
    }

    private class DefaultHandler extends ChannelInboundHandlerAdapter  {

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) {
            ByteBuf in = (ByteBuf) msg;
            try {
                // 读取请求报文
                byte[] bytes = new byte[in.readableBytes()];
                in.readBytes(bytes);

                // 处理请求报文，并获得响应报文
                byte[] output = handler.handle(bytes);
                ByteBuf buf = ctx.alloc().buffer(4 + output.length);
                buf.writeInt(output.length);
                buf.writeBytes(output);

                // 输出响应报文
                ctx.writeAndFlush(buf);
            } finally {
                in.release();
            }
        }

        @Override
        public void exceptionCaught(
                ChannelHandlerContext ctx, Throwable cause) {
            cause.printStackTrace();
            ctx.close();
        }

    }

    public static void main(String[] args) throws Exception {
        new RPCServer("localhost", 8888, new Handler() {
            @Override
            public byte[] handle(byte[] input) {
                try {
                    String s = new String(input, "UTF-8");
                    System.out.println(s);
                    return (s + " OK").getBytes("UTF-8");
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

}
