package com.wangyg.MiniHbase;

import java.io.IOException;

public class KeyValue implements Comparable<KeyValue> {
    //
    public static final int RAW_KEY_LEN_SIZE = 4;
    public static final int VAL_LEN_SIZE = 4;

    /**
     * key 键--转化成了 byte字节数组
     */
    private byte[] key;

    private long sequenceId;
    //操作类型, 是put类型还是delete类型
    private Op op;

    public static KeyValue parseFrom(byte[] bytes, int offset) throws IOException {
        if (bytes == null) {
            throw new IOException("buffer is null");
        }
        if (offset + RAW_KEY_LEN_SIZE >= bytes.length) {
            throw new IOException("Invalid offset or len. offset:" + offset + ", len =" + bytes.length);
        }
        //decode raw key length
        int pos = offset;

        int rawKeyLen = Bytes.toInt(Bytes.slice(bytes, pos, RAW_KEY_LEN_SIZE)); //slice切片
        pos += RAW_KEY_LEN_SIZE;

        //decode value length
        int valLen = Bytes.toInt(Bytes.slice(bytes, pos, VAL_LEN_SIZE));

        return null; //TODO
    }

    @Override
    public int compareTo(KeyValue kv) {
        if (kv == null) {
            throw new IllegalArgumentException("kv to compare should'nt bu null");
        }
        //key 小的放在前面
        int ret = Bytes.compare(this.key, kv.key);
        if (ret != 0) { //已经有比较结果了
            return ret;
        }
        if (this.sequenceId != kv.sequenceId) {
            return this.sequenceId > kv.sequenceId ? -1 : 1; //sequenceId 大的放在前面
        }
        //比较两个操作的 code
        if (this.op != kv.op) {
            return this.op.getCode() > kv.op.getCode() ? -1 : 1; //code 大的放在前面
        }
        return 0;
    }

    /**
     *
     */
    public enum Op {
        Put((byte) 0),
        Delete((byte) 1);
        private byte code;

        Op(byte code) {
            this.code = code;
        }

        public static Op code2Op(byte code) {
            switch (code) {
                case 0:
                    return Put;
                case 1:
                    return Delete;
                default:
                    throw new IllegalArgumentException("Unknown code: " + code);
            }
        }

        public byte getCode() {
            return this.code;
        }

    }
}
