package com.example.seckill.controller;

import com.example.seckill.service.SeckillActivityService;
import com.example.util.RespResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: chenq
 * @Description:
 * @Date: Created in 2021-07-09 16:42
 */
@RestController
@RequestMapping("/activity")
public class SeckillActivityController {
    @Autowired
    private SeckillActivityService seckillActivityService;

    /**
     * 获取有效的活动列表
     */
    @GetMapping
    public RespResult list() {
        return RespResult.ok(seckillActivityService.validActivity());
    }
}
