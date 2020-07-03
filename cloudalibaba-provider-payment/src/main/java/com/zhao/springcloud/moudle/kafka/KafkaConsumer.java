package com.zhao.springcloud.moudle.kafka;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @Description: kafka 消息消费
 * @Author: zhangzhao
 * @Date: 2020-05-10 20:56 
 **/
@Component
@AllArgsConstructor
@Slf4j
public class KafkaConsumer {
    @KafkaListener(topics = "${kafka.topic}", groupId = "${kafka.group}")
    public void data(ConsumerRecord consumerRecord) {
        Object value = consumerRecord.value();
        if (log.isInfoEnabled()) {
            log.info("消费者 1 offset {}, value {}", consumerRecord.offset(), consumerRecord.value());
        }
        if (null == value) {
            log.error("kafka消费数据为空");
        }
        log.info((String) value);
    }

    @KafkaListener(topics = "${kafka.topic}", groupId = "xxx")
    public void data1(ConsumerRecord consumerRecord) {
        Object value = consumerRecord.value();
        if (log.isInfoEnabled()) {
            log.info("消费者 2 offset {}, value {}", consumerRecord.offset(), consumerRecord.value());
        }
        if (null == value) {
            log.error("kafka消费数据为空");
        }
        log.info((String) value);
    }
}