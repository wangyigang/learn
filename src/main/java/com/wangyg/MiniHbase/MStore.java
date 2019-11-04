package com.wangyg.MiniHbase;



import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 定义好接口
 * PUT
 * delete
 */
public class MStore implements MiniHbase {
    //成员变量部分
    private ExecutorService pool; //线程池部分
    //存储内存部分数据的容器
    private MemStore memStore;
    private DiskStore diskStore; //磁盘部分存储
    private Compactor compactor; //后台守护线程，自动执行合并操作
    //序列id--会有多线程进行请求独立的id，要求不一致，防止出现重复现象
    private AtomicInteger sequenceId;
    //配置文件部分
    private Config conf;

    /**
     *
     * @return
     * @throws IOException
     */
    public MiniHbase open() throws  IOException{
        assert conf!= null;
        //创建线程池对象
        this.pool = Executors.newFixedThreadPool(conf.getMaxThreadPoolSize());
        //初始化diskStore
        this.diskStore = new DiskStore(conf.getDataDir(),conf.getMaxDiskFiles());
        this.diskStore.open(); //开启diskStore
        return null;
    }

    public void put(byte[] key, byte[] value) throws IOException {

    }

    @Override
    public KeyValue get(byte[] key) throws IOException {
        return null;
    }

    @Override
    public void delete(byte[] key) throws IOException {

    }

    @Override
    public Iter<KeyValue> scan(byte[] startKey, byte[] stopKey) throws IOException {
        return null;
    }
}
