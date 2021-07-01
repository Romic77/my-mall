package com.example.pay;

import com.example.pay.config.WeiXinPayConfig;
import com.github.wxpay.sdk.WXPay;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@MapperScan(basePackages = {"com.example.pay.mapper"})
/**
 * ./mqbroker.cmd -n 127.0.0.1:9876 autoCreateTopicEnable=true
 */
public class MallPayApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallPayApplication.class,args);
    }

    /****
     * 微信支付SDK对象
     * @return
     * @throws Exception
     */
    @Bean
    public WXPay wxPay(WeiXinPayConfig weiXinPayConfig) throws Exception {
        return new WXPay(weiXinPayConfig);
    }
}
