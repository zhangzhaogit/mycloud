import com.zz.rocketmq.CloudRocketmqApplication;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description:
 * @Author: zhangzhao
 * @Date: 2020-07-02 15:09
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CloudRocketmqApplication.class)
public class MqProduceTest {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    private static String topic = "zz_topic";

    /**
     * 发送同步消息
     */
    @Test
    public void syncSend() {
        //第一个参数是发送的目的地，一般是放topic，也可以放topic:tag;第二个参数是消息
        SendResult sendResult = rocketMQTemplate.syncSend(topic, "Hello, sychronized message!");
        System.out.println("同步消息的结果：" + sendResult);
    }

    /**
     * 发送异步消息
     */
    @Test
    public void asyncSend() {
        //第一个参数是发送的目的地，一般是放topic，也可以放topic:tag;第二个参数是消息；
        //第三个参数是异步消息发送结果的回调
        rocketMQTemplate.asyncSend(topic, "Hello, asychronized message!", new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                System.out.println("异步消息发送成功，发送结果："+sendResult);
            }

            @Override
            public void onException(Throwable throwable) {
                System.out.println("异步消息发送失败，消息回调");
            }
        });
    }
    @Test
    public void sendMsg() {
        //发送oneway消息
        //oneway消息就是只管发送，不管发送的结果如何
        rocketMQTemplate.sendOneWay(topic,"Hello, oneway message!");

        //发送带有tag的消息
        SendResult tagResult1 = rocketMQTemplate.syncSend(topic + ":test1", "Hello, tags:test1 message!");
        System.out.println("带有tag:test1的消息发送结果："+tagResult1);

        rocketMQTemplate.convertAndSend(topic, "hell aaa");
    }
}