package com.wangyg.NettyDemos.decoder.string;

import com.wangyg.netty.basic.util.RandomUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Test;
import java.nio.charset.Charset;


/**
 * 通过ReplayingDecoder解码器，可以正确的解码分包后的ByteBuf数据包，但是，实际开发过程中，不太建议继承这个类
 * 原因是：
 * 1.可能会抛出异常
 * 2. 在数据解析逻辑复杂的应用场景,ReplayingDecoder在解析速度上相对较差
 *
 */
public class StringreplayDecoderTester {
    static String content = "疯狂创客圈：高性能学习社群!";


    @Test
    public void testStringReplayDecoder() {
        ChannelInitializer i = new ChannelInitializer<EmbeddedChannel>() {
            protected void initChannel(EmbeddedChannel ch) {
                ch.pipeline().addLast(new StringReplayDecoder());
                ch.pipeline().addLast(new StringProcessHandler());
            }
        };
        EmbeddedChannel channel = new EmbeddedChannel(i);
        byte[] bytes = content.getBytes(Charset.forName("utf-8"));
        for (int j = 0; j < 100; j++) {
            //1-3之间的随机数
            int random = RandomUtil.randInMod(3);
            ByteBuf buf = Unpooled.buffer();
            buf.writeInt(bytes.length * random);
            for (int k = 0; k < random; k++) {
                buf.writeBytes(bytes);
            }
            channel.writeInbound(buf);
        }
        try {
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
