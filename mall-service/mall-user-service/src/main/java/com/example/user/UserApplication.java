package com.example.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: chenq
 * @Description:
 * @Date: Created in 2021-06-24 16:17
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.example.mapper"})
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class,args);
    }
}
