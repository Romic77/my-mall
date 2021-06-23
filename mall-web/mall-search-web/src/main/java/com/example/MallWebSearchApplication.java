package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author: chenq
 * @Description: http://localhost:8085/web/search
 * @Date: Created in 2021-06-21 10:14
 */
@SpringBootApplication
@EnableFeignClients(basePackages = "com.example")
public class MallWebSearchApplication {
    public static void main(String[] args) {
        SpringApplication.run(MallWebSearchApplication.class, args);
    }
}
