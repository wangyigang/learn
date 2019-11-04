package com.wangyg.NettyDemos.CombinedChannelDuplxHandler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import util.Logger;


public class Integer2ByteEncoder extends MessageToByteEncoder<Integer> {
    /**
     *
     * @param channelHandlerContext
     * @param integer
     * @param byteBuf
     * @throws Exception
     */
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Integer integer, ByteBuf byteBuf) throws Exception {
        byteBuf.writeInt(integer);
        Logger.info("encoder Integer="+ integer);
    }
}
