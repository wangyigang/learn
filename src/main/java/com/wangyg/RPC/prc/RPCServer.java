package com.wangyg.RPC.prc;


public class RPCServer {

    public static  interface  Handler{
        /**
         * @param input 请求报文
         * @return
         */
        byte[] handle(byte[] input);
    }
    private Handler handler;
    private String host; //主机Ip
    private int port; //端口
}
