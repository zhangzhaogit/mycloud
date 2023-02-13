package com.zhao.springcloud.moudle.kafka;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.boot.autoconfigure.kafka.ConcurrentKafkaListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.ConsumerAwareErrorHandler;

/**
 * @Description:
 * @Author: zhangzhao
 * @Date: 2023-02-13 09:42
 **/
@Configuration
public class KafkaCallback {


    /**
     * 消费失败消息最大重试15次，存入到死信队列中
     *
     * @param configurer kafkaConsumerFactory kafkaTemplate
     * @return factory
     */
    @Bean("kafkaListenerContainerFactory")
    public ConcurrentKafkaListenerContainerFactory<?, ?> kafkaListenerContainerFactory(
            ConcurrentKafkaListenerContainerFactoryConfigurer configurer, ConsumerFactory<Object, Object> kafkaConsumerFactory, KafkaTemplate<Object, Object> template) {
        ConcurrentKafkaListenerContainerFactory<Object, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        configurer.configure(factory, kafkaConsumerFactory);
        // 自定义消费失败处理
        factory.setErrorHandler(new GlobalErrorHandler());
        // 消费失败自动发送到topic: topicName.DLT 队列
//        factory.setErrorHandler(new SeekToCurrentErrorHandler(new DeadLetterPublishingRecoverer(template), new FixedBackOff(0L, 1)));
        return factory;

    }

    /**
     * 自定义消费失败处理
     */
    public class GlobalErrorHandler implements ConsumerAwareErrorHandler {
        @Override
        public void handle(Exception thrownException, ConsumerRecord<?, ?> consumerRecord, Consumer<?, ?> consumer) {
            System.out.println("消费失败回调 kafkaListenerContainerFactory :" + consumerRecord.toString());
        }

    }


}
