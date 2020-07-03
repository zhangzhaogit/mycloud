import com.zz.kafka.KafkaApplication;
import com.zz.kafka.module.KafkaSendResultHandler;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @Description: 消息生产者
 * @Author: zhangzhao
 * @Date: 2020-07-03 13:17
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = KafkaApplication.class)
@Slf4j
public class KafkaProduce {

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private KafkaSendResultHandler sendResultHandler;

    /**
    * @Description:  异步消息发送，回调方法为 sendResultHandler
    * @Param: []
    * @Return: void
    * @Author: zhangzhao
    * @Date: 2020/7/3 0003 下午 13:37
    * @Version: 3.0
    */
    @Test
    public void kafkaSendMsg() {
        try {
            kafkaTemplate.setProducerListener(sendResultHandler);
            kafkaTemplate.send("topic-test-01", "这是测试的数据==>"+ "zhao");
            log.info("kafka 消息发送成功");
        }catch (Exception e){
            e.printStackTrace();
            log.error("出错！！！！！！！！！！！");
        }
    }

    /**
    * @Description:  同步发送
    * @Param: []
    * @Return: void
    * @Author: zhangzhao
    * @Date: 2020/7/3 0003 下午 13:39
    * @Version: 3.0
    */
    @Test
    public void kafkaSyncSendMsg() {
        try {
            kafkaTemplate.setProducerListener(sendResultHandler);
            SendResult<String, String> result = kafkaTemplate.send("topic-test-01", "这是测试的数据==>" + "zhao").get();
            log.info("kafka 消息发送成功:" + result.toString());
        }catch (Exception e){
            e.printStackTrace();
            log.error("出错！！！！！！！！！！！");
        }
    }
}


