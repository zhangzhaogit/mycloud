package com.zhao.springcloud.service.strategy;/**
 * @Author zhangzhao
 * @Date 2023/2/19 12:11
 */

import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description:
 * @Author: zhangzhao
 * @Date: 2023-02-19 12:11
 **/
@Component("CSV")
public class CSVParser implements Parser {

    @Override
    public List parse(ContentType contentType) {
        System.out.println("=========================================csv parse=========================================");
        return null;
    }
}