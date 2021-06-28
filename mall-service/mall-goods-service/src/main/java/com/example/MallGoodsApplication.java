package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * http://localhost:8081/
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.example.mall.mapper"})
@EnableCaching
public class MallGoodsApplication {
    public static void main(String[] args) {
        SpringApplication.run(MallGoodsApplication.class,args);
    }
}
