package com.zhao.springcloud;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
/**
 * @Description 网关
 * @Author zhangzhao
 * @Date 9:59 2020/5/3
 */
@SpringBootApplication
@EnableDiscoveryClient
public class GatewayMain9527 {
    public static void main(String[] args) {
        SpringApplication.run(GatewayMain9527.class,args);
    }
}