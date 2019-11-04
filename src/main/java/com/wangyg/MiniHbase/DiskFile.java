package com.wangyg.MiniHbase;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class DiskFile implements Closeable {
    // 文件大小         块个数             块便宜                 块偏移
    // fileSize(8B)+ blockCount(4B) + blockIndexOffset(8B) + blockIndexOffset(8B) + DISK_FILE_MAGIC(8B)
    public static final int TRAILER_SIZE = 8 + 4 + 8 + 8 + 8; //记录文件各方面大小
    private static final long DISK_FILE_MAGIC = 0xFAC881234221FFA9L;



    //随机访问文件类
    /**
     * 该类是Java语言中功能最为丰富的文件访问类，它提供了众多的文件访问方法，RandomAccessFile类支持 随机访问的方式
     *  这里的随机 是指可以跳转到文件的任意位置处 读写数据，在访问一个文件的时候，不必把文件从头读到尾， 而是希望访问一个数据库一样
     *  随心所欲 的访问一个文件的某个部分, 这时候使用RandomAccessFile类就是最佳选择
     */
    private RandomAccessFile in;
    //fname --文件名称
    private String fname;


    //文件的大小
    private long fileSize;
    // 块个数
    private int blockCount;
    //块的索引偏移
    private long blockIndexOffset;
    //块的索引大小
    private long blockIndexSize;


    /**
     * 重写close方法
     * @throws IOException
     */
    @Override
    public void close() throws IOException {
        if(in != null){
            in.close();
        }
    }

    /**
     * 传入的绝对路径 -包含文件名称，应该是
     * @param fileName
     * @throws IOException
     */
    public void open(String fileName) throws  IOException{
        this.fname = fileName;

        File f = new File(this.fname);
        this.in = new RandomAccessFile(f, "r"); //对传入的文件创建 RandomAccessFile

        this.fileSize = f.length();
        assert  fileSize > TRAILER_SIZE;
        //进行寻位置
        in.seek(fileSize - TRAILER_SIZE);

        byte[] buffer = new byte[8];
        assert in.read(buffer) == buffer.length;
        assert this.fileSize == Bytes.toLong(buffer);  //assert 断言， 文件大小 和数组大小


        buffer = new byte[4];
        assert in.read(buffer) == buffer.length;
        this.blockCount = Bytes.toInt(buffer);

        buffer = new byte[8];
        assert in.read(buffer) == buffer.length;
        this.blockIndexOffset = Bytes.toLong(buffer);

        buffer = new byte[8];
        assert in.read(buffer) == buffer.length;
        this.blockIndexSize = Bytes.toLong(buffer);

        buffer = new byte[8];
        assert in.read(buffer) == buffer.length;
        assert DISK_FILE_MAGIC == Bytes.toLong(buffer);

        // TODO Maybe a large memory, and overflow
        buffer = new byte[(int) blockIndexSize];
        in.seek(blockIndexOffset);
        assert in.read(buffer) == blockIndexSize;

        int offset = 0;
        do {
            BlockMeta meta= BlockMeta.parseFrom(buffer, offset);
        }while (true) ;//TODO
    }


    public static class  BlockMeta implements  Comparable<BlockMeta>{

        private KeyValue lastKV;

        /**
         * 进行解析--返回blockMeta 块的元数据信息
         * @param buf
         * @param offset
         * @return
         * @throws IOException
         */
        public static BlockMeta parseFrom(byte[] buf, int offset) throws  IOException {
            int pos = offset;
            return null ; //TODO
        }


        /**
         * 使用KV 进行比较的方式
         * @param o
         * @return
         */
        @Override
        public int compareTo(BlockMeta o) {
            return 0; //TODO
        }
    }
}
