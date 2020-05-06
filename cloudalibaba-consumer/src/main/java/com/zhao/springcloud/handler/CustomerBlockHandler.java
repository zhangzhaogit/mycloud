package com.zhao.springcloud.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import common.CommonResult;
/**
 * @Description
 * @Author zhangzhao
 * @Date 15:21 2020/5/3
 */
public class CustomerBlockHandler {

    public static CommonResult handlerException(BlockException exception){
        return new CommonResult(4444,"按客户自定义，global handlerException-----1");
    }
}