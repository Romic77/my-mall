package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: chenq
 * @Description:
 * @Date: Created in 2021-07-19 10:28
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.example.mapper"})
public class MallDWApplication {
    public static void main(String[] args) {
        SpringApplication.run(MallDWApplication.class, args);
    }
}
