package com.wangyg.NettyDemos.MyJsonUtils;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.io.UnsupportedEncodingException;

/**
 * 自定义工具类，将Gson和 fastJson进行封装，
 */
public class MyJsonUtil {
    //谷歌 Gson
    static Gson gson = null;

    static {
        //不需要html escape
        gson  = new GsonBuilder().disableHtmlEscaping().create();
    }

    //Object 对象转成Json字符串后，进一步转成字节数组
    public static byte[] Object2JsonBytes(Object obj) {
        //把对象转成JSON
        String json = pojoToJson( obj);
        try {
            return json.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 反向操作： 将字节数组，转为 JSON字符串，转成Object对象，
     * @param
     * @return
     */
    public static <T> T JsonBytes2Object(byte[] bytes, Class<T> tClass) {
        //字节数组，转成JSON字符串
        try {
            String json = new String(bytes, "UTF-8");
            T t = jsonToPOJO(json, tClass);
            return t;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 使用泛型进行处理--使用阿里Fastjson将字符串转成POJO对象
     * @param json
     * @param tClass
     * @param <T>
     * @return
     */
    private static <T> T jsonToPOJO(String json, Class<T> tClass) {
        T t = JSONObject.parseObject(json, tClass);
        return t;
    }

    /**
     *  使用Gson将POJO转成字符串
     * @param obj
     * @return
     */
    private static String pojoToJson(Object obj) {
        String json = gson.toJson(obj);
        return json;
    }
}
