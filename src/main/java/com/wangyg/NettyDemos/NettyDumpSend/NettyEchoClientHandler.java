package com.wangyg.NettyDemos.NettyDumpSend;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import util.Logger;

public class NettyEchoClientHandler  extends ChannelInboundHandlerAdapter {
    //静态实例对象
    public static final NettyEchoClientHandler INSTANCE = new NettyEchoClientHandler();

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = (ByteBuf) msg;
        int len = in.readableBytes();
        byte[] arr = new byte[len];

        in.getBytes(0, arr);
        Logger.info(" clinent recieved " + new String(arr, "UTF-8"));
        in.release(); //进行释放操作
    }
}
