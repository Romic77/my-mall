package com.example.pay.config;

import com.example.util.Signature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @Author: chenq
 * @Description:
 * @Date: Created in 2021-07-01 13:23
 */
@Configuration
public class SecurityConfig {
    @Value("payconfig.aes.skey")
    private String skey;
    @Value("payconfig.aes.salt")
    private String salt;

    @Bean
    public Signature signature() {
        return new Signature(skey, salt);
    }
}
