package com.zhao.springcloud.moudle.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.support.ProducerListener;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * @Description: kafka 消息发送回调
 * @Author: zhangzhao
 * @Date: 2020-05-10 21:35 
 **/
@Component
@Slf4j
public class KafkaSendResultHandler implements ProducerListener {

    @Override
    public void onSuccess(ProducerRecord producerRecord,
                          RecordMetadata recordMetadata) {
        log.info("Message send success : " + producerRecord.toString());
    }

    @Override
    public void onError(ProducerRecord producerRecord, @Nullable RecordMetadata recordMetadata, Exception exception) {
        log.info("Message send error : " + producerRecord.toString());
    }

}