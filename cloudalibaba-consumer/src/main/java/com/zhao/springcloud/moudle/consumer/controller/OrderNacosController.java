package com.zhao.springcloud.moudle.consumer.controller;

import com.zhao.springcloud.feign.FeignService;
import com.zhao.springcloud.moudle.consumer.service.ConsumerService;
import common.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description //TODO
 * @Author zhangzhao
 * @Date 9:46 2020/5/3
 */
@RestController
@RequestMapping("/consumer")
public class OrderNacosController {

    @Autowired
    private FeignService feignService;
    @Autowired
    private ConsumerService consumerService;

    @GetMapping("/payment/nacos/{id}")
    public String paymentInfo(@PathVariable("id") Long id){
        return feignService.getPaymentInfo(id);
    }

    @GetMapping("/getAllConsumerInfo")
    public CommonResult getAllConsumerInfo(){
        return consumerService.getAllConsumerInfo();
    }


}