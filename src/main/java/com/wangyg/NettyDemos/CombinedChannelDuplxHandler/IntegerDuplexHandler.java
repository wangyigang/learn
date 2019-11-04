package com.wangyg.NettyDemos.CombinedChannelDuplxHandler;

import com.wangyg.NettyDemos.decoder.Byte2IntegerDecoder;
import io.netty.channel.CombinedChannelDuplexHandler;
public class IntegerDuplexHandler extends CombinedChannelDuplexHandler<
        Byte2IntegerDecoder,
        Integer2ByteEncoder>
{
    public IntegerDuplexHandler() {
        super(new Byte2IntegerDecoder(), new Integer2ByteEncoder());
    }
}
