package com.example.seckill;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: chenq
 * @Description:
 * @Date: Created in 2021-07-09 16:36
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.example.seckill.mapper"})
public class MallSeckillApplication {
    public static void main(String[] args) {
        SpringApplication.run(MallSeckillApplication.class, args);
    }
}
