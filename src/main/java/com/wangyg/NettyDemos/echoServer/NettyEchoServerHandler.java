package com.wangyg.NettyDemos.echoServer;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.jboss.netty.channel.ChannelHandler;
import util.Logger;

/**
 * 标注一个channel handler可以被多个channel安全地共享
 *
 * 会先服务器处理器的逻辑分为两步
 *:
 * 第一步： 从channelRead方法的msg参数
 * 第二步： 调用ctx.channel().writeAndFlush()把数据写回到客户端
 *
 */
@ChannelHandler.Sharable //标注一个channel handler可以被多个channel安全的共享：共享就是多个通道的流水线可以加入同一个Handler业务处理器实例
//而这种操作，Netty默认是不允许的, 很多场景都需要Handler业务处理器实例共享，例如： 一个服务器处理十万以上的通道，如果一个通道都新建很多
//重复的Handler实例，就会浪费很多宝贵的空间，降低服务器的性能
public class NettyEchoServerHandler  extends ChannelInboundHandlerAdapter {
    public static final NettyEchoServerHandler INSTANCE = new NettyEchoServerHandler();
    /**
     * msg 的类型是由流水线的上一站决定的，
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = (ByteBuf) msg;
        Logger.info("msg type: " + (in.hasArray()?"堆内存":"直接内存"));

        int len = in.readableBytes();
        byte[] arr = new byte[len];
        in.getBytes(0, arr);
        Logger.info("server received: " + new String(arr, "UTF-8"));

        /**
         * 回写给客户端:
         * 直接复用前面的msg实例即可
         * 上一步调用了getBytes，不影响ByteBuf的数据指针，因此可以继续使用,所以此处调用了ctx.writeAndFlush
         * 将msg写回到客户端
         */
        //写回数据，异步任务
        Logger.info("写回前，msg.refCnt:" + ((ByteBuf) msg).refCnt());
        ChannelFuture f = ctx.writeAndFlush(msg); //调用wrteAndFlush
        f.addListener((ChannelFuture futureListener) -> {
            Logger.info("写回后，msg.refCnt:" + ((ByteBuf) msg).refCnt());
        });
    }
}
