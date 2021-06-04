package com.example.mall;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StartConfig {

    /****
     * 分页拦截器
     */
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        PaginationInterceptor pageInterceptor = new PaginationInterceptor();
        // 设置数据类型
        pageInterceptor.setDbType(DbType.MYSQL);
        return pageInterceptor;
    }
}
