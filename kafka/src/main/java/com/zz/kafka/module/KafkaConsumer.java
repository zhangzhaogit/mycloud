package com.zz.kafka.module;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * @Description: 消费者
 * @Author: zhangzhao
 * @Date: 2020-07-03 13:12
 **/
@Service
@Slf4j
public class KafkaConsumer {
    /**
    * @Description:  消息回调
    * @Param: [consumerRecord]
    * @Return: void
    * @Author: zhangzhao
    * @Date: 2020/7/3 0003 下午 13:33
    * @Version: 3.0
    */
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

    /**
     * 不同 groupId 即可实现消息订阅
     * @param consumerRecord
     */
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