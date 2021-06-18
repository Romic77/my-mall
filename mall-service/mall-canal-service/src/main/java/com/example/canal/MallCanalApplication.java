package com.example.canal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author: chenq
 * @Description:
 * @Date: Created in 2021-06-18 13:17
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableFeignClients(basePackages = {"com.example"})
public class MallCanalApplication {
    public static void main(String[] args) {
        SpringApplication.run(MallCanalApplication.class, args);
    }
}
