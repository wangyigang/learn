package com.wangyg.NettyDemos.decoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import util.Logger;

import java.util.List;

public class Byte2IntegerDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf in, List<Object> list) throws Exception {
        while (in.readableBytes() >= 4) {
            int i = in.readInt();
            Logger.info("解码出一个整数:" + i);
            list.add(i); //将解码出的结果添加到list中，后面会传递到 下一个inbound中
        }
    }
}
