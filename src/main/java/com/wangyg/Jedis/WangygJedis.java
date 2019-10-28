package com.wangyg.Jedis;

/**
 * 自定义一些Jedis操作
 */
public class WangygJedis {

    WangygSocket socket = new WangygSocket("hadoop102", 6379);


    public String set(String key, String value) {
        socket.send(commandUtil(Resp.command.SET, key.getBytes(), value.getBytes()));
        return socket.read();
    }

    public String get(String key) {
        socket.send(commandUtil(Resp.command.GET, key.getBytes()));
        return socket.read();
    }

    public String incr(String key) {
        socket.send(commandUtil(Resp.command.INCR, key.getBytes()));
        return socket.read();
    }

    /**
     * @param command
     * @param bytes
     * @return
     */
    public static String commandUtil(Resp.command command, byte[]... bytes) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Resp.star).append(1 + bytes.length).append(Resp.line);
        stringBuilder.append(Resp.StringLength).append(command.toString().length()).append(Resp.line);
        stringBuilder.append(command.toString()).append(Resp.line);
        for (byte[] aByte : bytes) {
            stringBuilder.append(Resp.StringLength).append(aByte.length).append(Resp.line);
            stringBuilder.append(new String(aByte)).append(Resp.line);
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        WangygJedis lubanJedis = new WangygJedis();
        System.out.println(lubanJedis.set("taibai2", "123456"));
//      System.out.println(lubanJedis.set("taibai1", "123456"));
//      System.out.println(lubanJedis.get("taibai"));
//      System.out.println(lubanJedis.incr("lock"));
    }
}
