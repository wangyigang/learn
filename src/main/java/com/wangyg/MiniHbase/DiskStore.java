package com.wangyg.MiniHbase;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DiskStore  implements Closeable {
    //正则表达式  pattern
    private static  final Pattern DATA_FILE_RE = Pattern.compile("data\\.[0-9]+]");  //陪陪data.1   数字类型的


    //使用list -列表存储磁盘中文件句柄 名称.
    private List<DiskFile> diskFiles;
    //数据目录
    private String dataDir;
    //最大的文件个数
    private  int maxDiskFiles;
    //原子 long，  最大的文件ID
    private volatile AtomicLong maxFileId;

    //构造函数
    public DiskStore(String dataDir, int maxDiskFiles){
        this.dataDir = dataDir;
        this.diskFiles = new ArrayList<>();
        this.maxDiskFiles = maxDiskFiles;
    }

    public void open()  throws  IOException{
        File[] files = listDiskFiles();

        for (File f : files) {
            DiskFile df = new DiskFile();
            df.open(f.getAbsolutePath());
            diskFiles.add(df);
        }
        maxFileId= new AtomicLong(getMaxDiskId());

    }

    //TODO
    private long getMaxDiskId() {
        return 0;
    }

    /**
     *  列出目录下的所有文件， 保存在File数组中
     * @return
     */
    private File[] listDiskFiles() {
        File f = new File(this.dataDir);
        return f.listFiles(fname -> DATA_FILE_RE.matcher(fname.getName()).matches());
    }


    /**
     * 因为implments Closeable 所以要重写close方法
     * 实现closeable 方法
     * @throws IOException
     */
    @Override
    public void close() throws IOException {
        IOException closeException = null;

        for (DiskFile df : diskFiles) {
            try {
                df.close();
            }catch (IOException e){
                closeException = e;
            }
        }
        if(closeException != null){
            throw  closeException;
        }
    }


}
