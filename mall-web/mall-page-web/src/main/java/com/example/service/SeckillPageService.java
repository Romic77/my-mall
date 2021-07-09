package com.example.service;

import com.example.util.RespResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: chenq
 * @Description:
 * @Date: Created in 2021-06-23 13:38
 */

public interface SeckillPageService {


    void html(@PathVariable("id")String id) throws Exception;
}
