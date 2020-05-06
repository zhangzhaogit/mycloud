package com.zhao.springcloud.moudle.consumer.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author zhangzhao
 * @Date 2020/5/3 20:40
 */
@Mapper
@Repository
public interface ConsumerMapper {
    List<Map<String, Object>> getAllConsumerInfo();

}
