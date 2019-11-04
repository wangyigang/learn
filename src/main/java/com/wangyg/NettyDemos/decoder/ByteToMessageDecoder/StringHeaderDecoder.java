package com.wangyg.NettyDemos.decoder.ByteToMessageDecoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.junit.Test;

import java.util.List;

/**
 * byteBuf
 */
public class StringHeaderDecoder extends ByteToMessageDecoder {


    /**
     * 调用mark
     * @param channelHandlerContext
     * @param byteBuf
     * @param list
     * @throws Exception
     */
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        //可能字节小于4，消息头还没有填满，返回
        if (byteBuf.readableBytes() < 4) {
            return;
        }
        //消息头已经完整
        //在真正开始从缓冲区读取数据之前，调用markReaderIndex()设置回滚点
        //回滚点为消息头的ReadIndex读指针位置
        byteBuf.markReaderIndex();//设置读指针位置

        int length = byteBuf.readInt();

        if (byteBuf.readableBytes() < length) {
            //读指针回滚到消息头的readIndex位置处，未进行状态的保存
            byteBuf.resetReaderIndex(); //重设读指针位置
            return ;
        }

        //读取数据，编码成字符串
        byte[] inBytes = new byte[length];
        byteBuf.readBytes(inBytes, 0, length);
        list.add(new String(inBytes, "UTF-8"));

    }

}
