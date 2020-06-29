package com.zhao.elasticsearch.module;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @Author: zhangzhao
 * @Date: 2020-06-29 13:55
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String name;

    private Integer age;


}