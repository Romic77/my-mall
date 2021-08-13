package com.example.config;

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
                        "redis://192.168.116.135:7001",
                        "redis://192.168.116.135:7002",
                        "redis://192.168.116.135:7003");
        //创建RedissonClient
        return Redisson.create(config);
    }
}
