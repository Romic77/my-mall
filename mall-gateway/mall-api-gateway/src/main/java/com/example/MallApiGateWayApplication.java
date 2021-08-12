package com.example;

import com.example.limit.IpKeyResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @Author: chenq
 * @Description:
 * @Date: Created in 2021-07-21 16:45
 */
@SpringBootApplication
public class MallApiGateWayApplication {
    public static void main(String[] args) {
        SpringApplication.run(MallApiGateWayApplication.class, args);
    }

    @Bean
    public IpKeyResolver ipKeyResolver() {
        return new IpKeyResolver();
    }
}
