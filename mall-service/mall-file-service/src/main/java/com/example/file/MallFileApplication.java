package com.example.file;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author chenqi
 *
 * http://localhost:8082/file/upload
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class MallFileApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallFileApplication.class,args);
    }
}