package com.zhao.elasticsearch.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: zhangzhao
 * @Date: 2020-06-29 13:27
 **/
@Service
public class EsCreate {
    @Autowired
    RestHighLevelClient restHighLevelClient;

}