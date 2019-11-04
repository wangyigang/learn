package com.wangyg.NettyDemos.decoder.string;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

public class StringReplayDecoder extends ReplayingDecoder<StringReplayDecoder.Status> {
    enum Status {
        PARSE_1, PARSE_2
    }

    private int length;
    private byte[] inBytes;

    public StringReplayDecoder() {
        //构造函数中，需要初始化父类的state属性，表示当前阶段
        super(Status.PARSE_1) ;
    }

    /**
     * 每一次decode方法中的解码分为两个步骤：
     * 第一步骤： 解码出一个字符串的长度
     * 第二个步骤，按照第一个阶段的字符串长度解码出字符串的内容
     * @param ctx
     * @param in
     * @param out
     * @throws Exception
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

        switch (state()) {
            case PARSE_1:
                //第一步，从装饰器ByteBuf 中读取长度
                length = in.readInt(); //首先读取长度
                inBytes = new byte[length];
                // 进入第二步，读取内容
                // 并且设置“读指针断点”为当前的读取位置
                checkpoint(Status.PARSE_2);
                break;
            case PARSE_2:
                //第二步，从装饰器ByteBuf 中读取内容数组
                in.readBytes(inBytes, 0, length);
                out.add(new String(inBytes, "UTF-8")); //读取文件内容
                // 第二步解析成功，
                // 进入第一步，读取下一个字符串的长度
                // 并且设置“读指针断点”为当前的读取位置
                checkpoint(Status.PARSE_1);
                break;
            default:
                break;
        }
    }


}
