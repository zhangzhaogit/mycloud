package com.zz.rocketmq;

import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: zhangzhao
 * @Date: 2020-07-02 16:15
 **/
@Service
@RocketMQMessageListener(topic = "zz_topic",consumerGroup = "my_consumer")
public class Consumer implements RocketMQListener<MessageExt> {

    /**
     * MessageExt 限定数据类型，可改为RocketMQListener<MessageExt>
     * @param messageExt
     */
    @Override
    public void onMessage(MessageExt messageExt) {
        System.out.println("收到了消息："+new String(messageExt.getBody()));
    }
}