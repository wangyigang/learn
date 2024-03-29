package com.wangyg.NettyDemos.echoServer;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import util.Logger;

public class NettyEchoClientHandler  extends ChannelInboundHandlerAdapter {
    public static  final  NettyEchoClientHandler INSTANCE = new NettyEchoClientHandler();


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = (ByteBuf) msg;
        int len = in.readableBytes();
        byte[] arr = new byte[len];
        in.getBytes(0, arr);
        Logger.info("client received: " + new String(arr, "UTF-8"));
        in.release();
    }
}
