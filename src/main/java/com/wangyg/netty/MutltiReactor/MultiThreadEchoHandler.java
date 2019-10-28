package com.wangyg.netty.MutltiReactor;

import util.Logger;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * 引入一个线程池(ThreadPool) 业务处理的代码执行在自己的线程池中，彻底的做到
 * 业务处理线程和反应线程和反应器io事件的完全隔离
 */
public class MultiThreadEchoHandler implements Runnable {
    final SocketChannel channel;
    final SelectionKey sk;
    final ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
    static final int RECIEVING = 0, SENDING = 1;
    int state = RECIEVING;

    //我他吗就是一个傻逼

    //引入线程池
    static ExecutorService pool = Executors.newFixedThreadPool(4); //创建固定线程池-4个

    public MultiThreadEchoHandler(Selector selector, SocketChannel channel) throws IOException {
        this.channel = channel;
        channel.configureBlocking(false); //设置为非阻塞
        //仅仅取得选择键，后设置感兴趣的IO事件
        sk = channel.register(selector, 0);
        //将本handler作为sk选择键的附件，方便事件dispatch
        sk.attach(this);
        //向sk选择键注册read就绪事件
        sk.interestOps(SelectionKey.OP_READ);

        selector.wakeup(); //可以立刻进行返回操作
    }

    @Override
    public void run() {
        //异步任务，在独立的线程池中执行
        pool.execute(new AsyncTask()); //使用线程池，异步进行执行任务，只要将任务提交给线程池即可，不需要进行等待
    }
    //异步任务，不在Reactor线程中执行

    /**
     * 具体的异步执行方法
     */
    public synchronized void asyncRun() {
        //state状态在这个方法中进行改变
        //首先进入时，方法状态是recieving接收状态
        try {
            if (state == SENDING) {
                //写入通道中

                channel.write(byteBuffer);
                //写完后，准备从通道读取，bytebuffer切换成写模式
                byteBuffer.clear();
                //写完后，注册read就绪事件
                sk.interestOps(SelectionKey.OP_READ);
                //写完后，将状态改为接收状态
                state = RECIEVING;
            } else if (state == RECIEVING) {
                //从通道读取数据
                int lenght = 0;
                while ((lenght = channel.read(byteBuffer)) > 0) {
                    Logger.info(new String(byteBuffer.array(), 0, lenght));
                }
                //读完后，准备开始写入通道，bytebuffer切换成读模式
                byteBuffer.flip(); //切换成读模式
                //读完后，注册write写就绪事件
                sk.interestOps(SelectionKey.OP_WRITE);
                //修改状态，将状态改为发送状态
                state = SENDING;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //异步任务的内部类

    /**
     * 线程池中提交的是这个任务.
     * 实质上，内部调用的是asyncRun() 这个run方法进行执行
     */
    class AsyncTask implements Runnable {
        public void run() {
            MultiThreadEchoHandler.this.asyncRun(); //当前类的.this.asyncRun()
        }
    }
}
