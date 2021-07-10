package com.example.feign;

import com.example.util.RespResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: chenq
 * @Description:
 * @Date: Created in 2021-06-23 15:10
 */
@FeignClient("mall-page-web")
@RequestMapping("/page")
public interface SeckillPageFeign {
    /**
     * 生成静态页
     */
    @GetMapping("/seckill/goods/{id}")
    RespResult html(@PathVariable("id")String id) throws Exception;

    /***
     * 删除指定活动的页面
     */
    @DeleteMapping(value = "/seckill/goods/{acid}")
    RespResult deleByAct(@PathVariable("acid")String acid);
}
