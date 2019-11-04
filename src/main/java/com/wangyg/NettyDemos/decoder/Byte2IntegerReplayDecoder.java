package com.wangyg.NettyDemos.decoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;
import util.Logger;

import java.util.List;

public class Byte2IntegerReplayDecoder  extends ReplayingDecoder {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        int i= byteBuf.readInt();
        Logger.info("解码出一个整数：" + i);
        list.add(i);
    }
}
