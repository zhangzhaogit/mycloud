package com.zhao.springcloud.moudle.consumer.service.impl;
/**
 * @Author zhangzhao
 * @Date 2020/5/3 20:36
 */

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.zhao.springcloud.handler.CustomerBlockHandler;
import com.zhao.springcloud.moudle.consumer.mapper.ConsumerMapper;
import com.zhao.springcloud.moudle.consumer.service.ConsumerService;
import common.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 *@ClassName ConsumerServiceImpl
 *@Description TODO
 *@Author zhangzhao
 *@Date 2020/5/3 20:36
 *@Version 1.0
 */
@Service
public class ConsumerServiceImpl implements ConsumerService {

    @Autowired
    private ConsumerMapper consumerMapper;

    @Override
    @SentinelResource(value = "paymentInfoHandler",blockHandlerClass = CustomerBlockHandler.class,blockHandler = "handlerException")
    public CommonResult getAllConsumerInfo() {
//        redisTemplate.opsForValue().set("zz",6666666);
        List<Map<String, Object>> list = consumerMapper.getAllConsumerInfo();
        CommonResult<Object> result = new CommonResult<>();
        result.setData(list == null ? Collections.emptyList() : list );
        return result;
    }

    public CommonResult deal_testHotKey(BlockException exception){
        CommonResult<Object> result = new CommonResult<>();
        result.setCode(444);
        result.setMessage("服务被限流,o(╥﹏╥)o");
        return result;
    }
}
