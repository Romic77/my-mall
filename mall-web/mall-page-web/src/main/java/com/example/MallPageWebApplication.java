package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author: chenq
 * @Description:
 * @Date: Created in 2021-06-23 13:24
 */
@SpringBootApplication
@EnableFeignClients(basePackages = {"com.example"})
public class MallPageWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(MallPageWebApplication.class, args);
    }
}
