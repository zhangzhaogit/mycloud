package com.zhao.springcloud.moudle.kafka;/**
 * @Author zhangzhao
 * @Date 2023/2/13 17:20
 */

import org.apache.kafka.clients.consumer.Consumer;
import org.springframework.kafka.listener.KafkaListenerErrorHandler;
import org.springframework.kafka.listener.ListenerExecutionFailedException;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: zhangzhao
 * @Date: 2023-02-13 17:20 
 **/
@Component("kafkaErrorHandler")
public class KafkaErrorHandler implements KafkaListenerErrorHandler {

    @Override
    public Object handleError(Message<?> message, ListenerExecutionFailedException e) {
        return null;
    }

    @Override
    public Object handleError(Message<?> message, ListenerExecutionFailedException exception, Consumer<?, ?> consumer) {
        return null;
    }
}