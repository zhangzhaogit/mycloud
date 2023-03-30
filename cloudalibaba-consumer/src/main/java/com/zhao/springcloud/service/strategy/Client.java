package com.zhao.springcloud.service.strategy;/**
 * @Author zhangzhao
 * @Date 2023/2/19 12:13
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * @Description:
 * @Author: zhangzhao
 * @Date: 2023-02-19 12:13 
 **/
@Service
public class Client {

    @Autowired
    Map<String, Parser> parserFactory;

    @PostConstruct
    public void parse() {
        ContentType contentType = ContentType.valueOf("CSV");
        parserFactory.get(contentType.getParserName()).parse(contentType);
    }
}