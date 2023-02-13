package com.zhao.springcloud.moudle.kafka;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
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

    /**
    * @Description:  注意点： 消费失败没提交offset 超过重试次数（触发再平衡 需要百度查下），自动拉取下一条数据，但是项目重启会从头拉取数据因为一直没提交offset
    * @Param: [record, ack]
    * @Return: void
    * @Author: zhangzhao
    * @Date: 2023/2/13 17:32
    */
    @KafkaListener(topics = {"${kafka.topic}"}, groupId = "xxx",errorHandler = "KafkaErrorHandler")
    public void processMessage(ConsumerRecord<?, ?> record, Acknowledgment ack) {
        try {
            System.out.printf("topic is %s, offset is %d,partition is %s, value is %s \n", record.topic(), record.offset(),record.partition(), record.value());
            // 手动提交offset
            ack.acknowledge();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}