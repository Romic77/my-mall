package com.example.controller;

import com.example.service.SeckillPageService;
import com.example.util.RespResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/page")
public class SeckillPageController {
    @Autowired
    private SeckillPageService seckillPageService;
    /**
     * 生成秒杀商品详情页
     */
    @GetMapping("/seckill/goods/{id}")
    public RespResult page(@PathVariable("id")String id) throws Exception {
        seckillPageService.html(id);
        return RespResult.ok();
    }
}
