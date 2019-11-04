package com.wangyg.MiniHbase;

import java.io.IOException;

/**
 * 主类： 使用MiniHbase 最主要的思想
 */
public interface MiniHbase {

    /**
     * put操作
     * @param key
     * @param value
     * @throws IOException
     */
    void put(byte[] key, byte[] value) throws IOException;

    KeyValue get(byte[] key) throws  IOException;

    void delete(byte[] key) throws IOException;

    Iter<KeyValue> scan(byte[] startKey, byte[] stopKey) throws  IOException;

    /**
     * 实现default 默认接口
     * @return
     * @throws IOException
     */
    default  Iter<KeyValue> scan() throws  IOException{
        return scan(Bytes.EMPTY_BYTES, Bytes.EMPTY_BYTES);
    }

    /**
     * 迭代器接口
     * @param <KeyValue>
     */
    interface  Iter<KeyValue>{
        boolean hashNext() throws  IOException;
        KeyValue next() throws  IOException;
    }

    interface  Flusher{
        void  flush(Iter<KeyValue> it) throws  IOException;
    }

    /**
     * 抽象类： 自动执行compact任务
     */
    abstract  class Compactor extends Thread{
        public abstract void compact() throws IOException;
    }
}
