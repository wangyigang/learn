package com.wangyg.NettyDemos.MyJsonUtils;

import org.junit.Test;
import util.Logger;

import java.io.IOException;

/**
 * 使用POJO 类JsonMsg  实现从POJO对象到Json的序列化，反序列化的实践案例
 */
public class JsonMsgDemo {

    //构建Json对象
    public JsonMsg buildMsg() {
        JsonMsg user = new JsonMsg();
        user.setId(1000);
        user.setContent("疯狂创客圈:高性能学习社群");
        return user;
    }

    //序列化 serialization & 反序列化 Deserialization
    @Test
    public void serAndDesr() throws IOException {
        JsonMsg message = buildMsg(); //创建POJO对象
        //将POJO对象，序列化成字符串
        String json = message.convertToJson(); //转成json字符串
        //可以用于网络传输,保存到内存或外存
        Logger.info("json:=" + json);

        //JSON 字符串,反序列化成对象POJO
        JsonMsg inMsg = JsonMsg.parseFromJson(json); //然后再讲json字符串转成pojo对象
        Logger.info("id:=" + inMsg.getId());  //输出对象中的属性值
        Logger.info("content:=" + inMsg.getContent());
    }
}
