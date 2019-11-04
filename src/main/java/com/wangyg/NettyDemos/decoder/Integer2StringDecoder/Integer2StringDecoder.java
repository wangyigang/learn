package com.wangyg.NettyDemos.decoder.Integer2StringDecoder;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

public class Integer2StringDecoder extends MessageToMessageDecoder<Integer> {

    /**
     *
     * @param ch
     * @param integer
     * @param list
     * @throws Exception
     */
    @Override
    protected void decode(ChannelHandlerContext ch, Integer integer, List<Object> list) throws Exception {
        list.add(String.valueOf(integer));
    }
}
