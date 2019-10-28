package com.wangyg.Jedis;

/**
 * redis 协议
 */
public class Resp {
    public static final String star = "*";
    public static final String StringLength = "$";
    public static final String line = "\r\n";

    public static enum command{
        SET,GET,INCR
    }
}
