package com.zhao.auth.endpoint;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: zhangzhao
 * @Date: 2023-03-31 15:50 
 **/
@RestController
@AllArgsConstructor
public class GetwayRoutTest {

    @GetMapping("/z")
    public String z () {
        return "GetwayRoutTest";
    }

}