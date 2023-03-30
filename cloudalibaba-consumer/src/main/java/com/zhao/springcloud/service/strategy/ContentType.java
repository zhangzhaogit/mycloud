package com.zhao.springcloud.service.strategy;

import lombok.Getter;

/**
 * @Author zhangzhao
 * @Date 2023/2/19 12:10
 */
@Getter
public enum ContentType {
    CSV("CSV");
    private final String parserName;
    ContentType(String parserName) {
        this.parserName = parserName;
    }

}
