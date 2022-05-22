package com.zhao.springcloud.moudle.consumer.controller;

import com.zhao.springcloud.feign.FeignService;
import com.zhao.springcloud.moudle.consumer.service.ConsumerService;
import common.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description //TODO
 * @Author zhangzhao
 * @Date 9:46 2020/5/3
 */
@RestController
@RequestMapping("/consumer")
@RefreshScope   //SpringCloud原生注解 支持Nacos的动态刷新功能
public class OrderNacosController {

    @Autowired
    private FeignService feignService;
    @Autowired
    private ConsumerService consumerService;
    @Autowired
    private RedisTemplate redisTemplate;


    @Value("${config.info}")
    private String configInfo;
    @Value("${spring.cloud.sentinel.transport.port}")
    private String port;

    @GetMapping("/hello")
    public String hello() {
        return "hello spring security";
    }

    @GetMapping("/payment/nacos/{id}")
    public String paymentInfo(@PathVariable("id") Long id){
        return feignService.getPaymentInfo(id);
    }

    @GetMapping("/getAllConsumerInfo")
    public CommonResult getAllConsumerInfo(){
        return consumerService.getAllConsumerInfo();
    }

    @GetMapping("/getConfigInfo")
    public String getConfigInfo(String key){
//        redisTemplate.opsForValue().set(key, key+"xx");
        return "configInfo + port";
    }
}