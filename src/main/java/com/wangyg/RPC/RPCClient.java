package com.wangyg.RPC;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Random;

/**
 * Created by wangyg-b
 */

public class RPCClient {

    private Socket socket;
    private DataOutputStream os;
    private DataInputStream  is;

    /**
     * @param host 服务端绑定的主机
     * @param port 服务端绑定的端口
     */
    public RPCClient(String host, int port)throws Exception{
        socket = new Socket(host, port);
        socket.setKeepAlive(true);
        socket.setSendBufferSize(16 * 1024);
        socket.setReceiveBufferSize(16 * 1024);
        os = new DataOutputStream(socket.getOutputStream());
        is = new DataInputStream(socket.getInputStream());
    }

    /**
     * 向服务端发送请求并等待服务端返回响应
     * @param data 请求报文
     * @return 响应报文
     */
    public synchronized byte[] request(byte[] data) throws Exception {
        return request(data, 0, data.length);
    }

    /**
     * 向服务端发送请求并等待服务端返回响应
     * @param buffer 请求报文缓冲区
     * @param offset 请求报文在缓冲区中的偏移量
     * @param length 请求报文的长度
     * @return 响应报文
     */
    public synchronized byte[] request(
            byte[] buffer, int offset, int length) throws Exception {
        os.writeInt(length);
        os.write(buffer, offset, length);
        os.flush();

        int len = is.readInt();
        if (len < 0 || len > 8 * 1024 * 1024)
            throw new RuntimeException("invalid message length: " + len);

        byte[] rst = new byte[len];
        is.readFully(rst);
        return rst;
    }

    public synchronized void close() throws Exception {
        os.close();
        is.close();
        socket.close();
    }

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 10; i++) {
            final int index = i;
            new Thread() {
                public void run() {
                    try {
                        RPCClient client=new RPCClient("localhost",8888);
                        Random random = new Random();
                        int cnt = 0;
                        while (true) {
                            byte[] input = String.format("客户端%d:%d",
                                    index, ++cnt).getBytes("UTF-8");
                            byte[] rst = client.request(input);
                            System.out.println(new String(rst, "UTF-8"));
                            Thread.sleep(random.nextInt(3000));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }

}
