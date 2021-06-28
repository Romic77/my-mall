package com.example.pay;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.example")
/**
 * ./mqbroker.cmd -n 127.0.0.1:9876 autoCreateTopicEnable=true
 */
public class MallPayApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallPayApplication.class,args);
    }
}
