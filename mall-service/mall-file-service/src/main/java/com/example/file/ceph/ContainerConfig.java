package com.example.file.ceph;

import lombok.Data;
import org.javaswift.joss.client.factory.AccountConfig;
import org.javaswift.joss.client.factory.AccountFactory;
import org.javaswift.joss.client.factory.AuthenticationMethod;
import org.javaswift.joss.model.Account;
import org.javaswift.joss.model.Container;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @Author: chenq
 * @Description:
 * @Date: Created in 2021-06-09 10:05
 */
@Configuration
@Data
@ConfigurationProperties("ceph")
public class ContainerConfig {
    private String username;
    private String password;
    private String authUrl;
    private String defaultContainerName;

    /**
     * 1. 创建账号
     */
    @Bean
    public Account account(){
        AccountConfig accountConfig=new AccountConfig();
        accountConfig.setUsername(username);
        accountConfig.setPassword(password);
        accountConfig.setAuthUrl(authUrl);
        accountConfig.setAuthenticationMethod(AuthenticationMethod.BASIC);
        return new AccountFactory(accountConfig).createAccount();
    }

    /**
     * 2. 创建容器
     */
    @Bean
    public Container container(){
        Container container =account().getContainer(defaultContainerName);
        if(!container.exists()){
            return container.create();
        }
        return container;
    }

}
