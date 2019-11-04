package com.wangyg.NettyDemos.decoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

/**
 * 第一个阶段读取前面的整数
 *
 * 第二给阶段读取后面的整数，然后相加
 */
public class IntegerAddDecoder extends ReplayingDecoder<IntegerAddDecoder.Status> {

    enum Status{ //status： 表示阶段 状态
        PARSE_1, PARSE_2
    }


    private int first;
    private int second;

    /**
     * 构造函数
     */
    public IntegerAddDecoder(){
        super(Status.PARSE_1);  //构造函数中，需要初始化类state属性，表示当前阶段
    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        switch (state()) { //state()为Netty内部函数
            case PARSE_1:
                //从装饰器Bytebuf中读取数据
                first = byteBuf.readInt();
                //第一步解析成功
                //进入第二步，并设置  读指针断点 为当前的读取位置
                checkpoint(Status.PARSE_2); //checkpoint()为内部函数
                break;
            case PARSE_2:
                second = byteBuf.readInt();
                Integer sum = first + second;
                list.add(sum);
                checkpoint(Status.PARSE_1);
                break;
            default:
                break;
        }
    }


}
