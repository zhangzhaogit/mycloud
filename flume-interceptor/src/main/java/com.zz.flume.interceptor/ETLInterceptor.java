package com.zz.flume.interceptor;
/**
 * @Author zhangzhao
 * @Date 2022/6/28 22:01
 */

import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * @Description:
 * @Author: zhangzhao
 * @Date: 2022-06-28 22:01
 * 1. 实现接口
 * 2. 实现方法
 **/
public class ETLInterceptor implements Interceptor {


    private static final Logger logger = LoggerFactory.getLogger(ETLInterceptor.class);
    @Override
    public void initialize() {

    }

    @Override
    public Event intercept(Event event) {
        // 过滤event中的数据是否是json格式
        String log = new String(event.getBody(), UTF_8);
        logger.info(log);
        boolean flag = false;
        if (JSONUtils.isJSONValidate(log)) {
            return event;
        } else {
            logger.info(" ETLInterceptor json error");
            return null;
        }
    }

    @Override
    public List<Event> intercept(List<Event> list) {
        // 将处理过的event为null的移除
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
            return new ETLInterceptor();
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