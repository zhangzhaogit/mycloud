package com.zz.flume.interceptor;
/**
 * @Author zhangzhao
 * @Date 2022/6/30 19:44
 */

import com.alibaba.fastjson.JSONObject;
import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * @Description: 日志零点漂移解决
 * @Author: zhangzhao
 * @Date: 2022-06-30 19:44 
 **/
public class TimestampInterceptor implements Interceptor {

    private static final Logger logger = LoggerFactory.getLogger(TimestampInterceptor.class);
    @Override
    public void initialize() {

    }

    @Override
    public Event intercept(Event event) {
        // 修改headers中的时间戳 改为数据中的时间
        // 提供给hdfs sink 使用，控制输出文件的文件夹名称
        String log = new String(event.getBody(), UTF_8);
        if (JSONUtils.isJSONValidate(log)) {
            JSONObject jsonObject = JSONObject.parseObject(log);
            String timestamp = jsonObject.getString("ts");
            Map<String, String> headers = event.getHeaders();
            headers.put("timestamp", timestamp);
            return event;
        } else {
            logger.info(" TimestampInterceptor json error");
            logger.info(log);
            return null;
        }

    }

    @Override
    public List<Event> intercept(List<Event> list) {
        Iterator<Event> iterator = list.iterator();
        while (iterator.hasNext()){
            Event next = iterator.next();
            if (intercept(next) == null ) {
                iterator.remove();
            }
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