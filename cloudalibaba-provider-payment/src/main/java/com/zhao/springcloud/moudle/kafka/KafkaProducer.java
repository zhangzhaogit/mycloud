package com.zhao.springcloud.moudle.kafka;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

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
}