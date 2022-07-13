package com.zz.flume.interceptor.db;/**
 * @Author zhangzhao
 * @Date 2022/7/13 19:30
 */

import com.alibaba.fastjson.JSONObject;
import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.util.List;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * @Description:
 * @Author: zhangzhao
 * @Date: 2022-07-13 19:30 
 **/
public class TimestampInterceptor implements Interceptor {

    @Override
    public void initialize() {

    }

    @Override
    public Event intercept(Event event) {
        // 将event中的数据，里面的时间戳拿出来
        // 放入到headers中，提供给hdfs sink使用，控制输出文件路径
        byte[] body = event.getBody();
        String log = new String(body, UTF_8);
        JSONObject jsonObject = JSONObject.parseObject(log);
        Long ts = jsonObject.getLong("ts");
        String s = String.valueOf(ts * 1000);

        Map<String, String> headers = event.getHeaders();
        headers.put("timestamp", s);
        return event;
    }

    @Override
    public List<Event> intercept(List<Event> list) {
        for (Event event : list) {
            intercept(event);
        }
        return list;
    }

    @Override
    public void close() {

    }


    public static class Builder implements Interceptor.Builder{
        @Override
        public Interceptor build() {
            return new TimestampInterceptor();
        }

        /**
         * 读写的conf文件
         * @param context
         */
        @Override
        public void configure(Context context) {

        }
    }
}