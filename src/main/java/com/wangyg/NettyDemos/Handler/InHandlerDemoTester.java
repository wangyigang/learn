package com.wangyg.NettyDemos.Handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class InHandlerDemoTester {
    @Test
    public void testInHandlerLifeCircle() {
        final InHandlerDemo inhandler = new InHandlerDemo();
        //初始化处理器
        ChannelInitializer i = new ChannelInitializer() {
            @Override
            protected void initChannel(Channel channel) throws Exception {
                channel.pipeline().addLast(inhandler);
            }
        };
        //创建嵌入式通道
        EmbeddedChannel channel = new EmbeddedChannel(i);

        ByteBuf buf = Unpooled.buffer();

        buf.writeInt(1);
        //模拟入栈，写一个入站包
        channel.writeInbound(buf);
        channel.flush();
        //模拟入栈，再写一个入站包
        channel.writeInbound(buf);
        channel.flush();
        //通道关闭
        channel.close();

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
