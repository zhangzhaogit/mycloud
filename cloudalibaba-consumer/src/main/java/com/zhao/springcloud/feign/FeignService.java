package com.zhao.springcloud.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

/**
 * @Author zhangzhao
 * @Date 2020/5/3 13:29
 */
@Service
@FeignClient("nacos-payment-provider")
public interface FeignService {

//    @RequestMapping(value = "/provider/payment/nacos/{id}",method = RequestMethod.GET)

    /**
     *
     * @param id
     * @return
     */
    @GetMapping("/provider/payment/nacos/{id}")
    String getPaymentInfo(@PathVariable("id") Long id);

}
