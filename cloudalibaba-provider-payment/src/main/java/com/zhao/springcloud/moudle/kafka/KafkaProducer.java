package com.zhao.springcloud.moudle.kafka;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Description: kafka 消息生产
 * @Author: zhangzhao
 * @Date: 2020-05-10 20:43 
 **/
@Component
@AllArgsConstructor
@Slf4j
public class KafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private KafkaSendResultHandler sendResultHandler;

    public void data(String data){
        try {
            kafkaTemplate.setProducerListener(sendResultHandler);
            kafkaTemplate.send("topic-test-01", "这是测试的数据==>"+data );
        }catch (Exception e){
            e.printStackTrace();
            log.error("出错！！！！！！！！！！！");
        }

    }

    public void sendMessage2(@PathVariable("message") String callbackMessage) {
        kafkaTemplate.send("topic1", callbackMessage).addCallback(success -> {
            // 消息发送到的topic
            String topic = success.getRecordMetadata().topic();
            // 消息发送到的分区
            int partition = success.getRecordMetadata().partition();
            // 消息在分区内的offset
            long offset = success.getRecordMetadata().offset();
            System.out.println("发送消息成功:" + topic + "-" + partition + "-" + offset);
        }, failure -> {
            System.out.println("发送消息失败:" + failure.getMessage());
        });
    }



}