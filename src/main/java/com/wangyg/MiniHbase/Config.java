package com.wangyg.MiniHbase;



/**
 * 配置文件
 */
public class Config {
    //最大线程数量
    private int maxThreadPoolSize=5;
    // 数据路径
    private  String dataDir = "MiniBase";
    //最大落在磁盘上的文件个数
    private int maxDiskFiles=10;
    //最大刷写flush 次数
    private int flushMaxRetries =10;
    //最大内存容量
    private long maxMemstoreSize = 16*1024*1024;

    public Config setMaxMemstoreSize(long maxMemstoreSize) {
        this.maxMemstoreSize = maxMemstoreSize;
        return this;
    }

    public long getMaxMemstoreSize() {
        return this.maxMemstoreSize;
    }

    public Config setFlushMaxRetries(int flushMaxRetries){
        this.flushMaxRetries = flushMaxRetries;
        return this;
    }

    public int getFlushMaxRetries(){
        return this.flushMaxRetries;
    }
    /**
     * 对外提供的方法，获取最大的线程个数
     * @return
     */
    public int getMaxThreadPoolSize() {
        return this.maxThreadPoolSize ;
    }

    /**
     * set方法
     * @param maxThreadPoolSize
     * @return
     */
    public Config setMaxThreadPoolSize(int maxThreadPoolSize){
        this.maxThreadPoolSize= maxThreadPoolSize;
        return this;
    }

    /**
     * 获取
     * @return
     */
    public String getDataDir() {
        return this.dataDir;
    }

    /**
     * 设置数据路径
     * @param dataDir
     * @return
     */
    public Config setDataDir(String dataDir){
        this.dataDir = dataDir;
        return this;
    }

    public int getMaxDiskFiles() {
        return this.maxDiskFiles;
    }
}
