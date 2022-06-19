package com.zhao.springcloud.moudle.consumer.controller;

import com.zhao.springcloud.moudle.kafka.KafkaProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Description //TODO
 * @Author zhangzhao
 * @Date 9:36 2020/5/3
 */
@RestController
@Slf4j
@RequestMapping("/provider")
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private KafkaProducer kafkaProducer;

    @GetMapping("/payment/nacos/{id}")
    public String getPayment(@PathVariable("id") Integer id){
        return "nacos registry,serverPort: "+ serverPort+"\t id"+id;
    }

    @GetMapping("/kafkaProducer")
    public void kafkaProducer(String msg){
        kafkaProducer.data(msg);
    }

    @GetMapping("/error")
    public void getError(String msg){
        int i = 1 / 0;
    }

    @PostMapping("/post")
    public void post(@RequestBody Map<String,Object> map){
        Integer role = 0;
        boolean role1 = role.equals(map.get("role"));
        System.out.println(role1);
    }

}
