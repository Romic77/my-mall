package com.example.init;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissLock {


    /***
     * 创建RedissonClient对象
     *      创建锁、解锁
     * @return
     */
    @Bean
    public RedissonClient redissonClient(){
        //创建Config
        Config config = new Config();
        //集群实现
        config.useClusterServers()
                .setScanInterval(2000)
                .addNodeAddress(
                        "redis://localhost:6379")
                .setPassword("123456");
        //创建RedissonClient
        return Redisson.create(config);
    }
}