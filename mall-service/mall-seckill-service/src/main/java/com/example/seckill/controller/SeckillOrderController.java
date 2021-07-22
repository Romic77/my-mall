package com.example.seckill.controller;

import com.example.seckill.service.SeckillOrderService;
import com.example.util.RespResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: chenq
 * @Description:
 * @Date: Created in 2021-07-09 16:43
 */
@RestController
@RequestMapping("/seckill/order")
public class SeckillOrderController {
    @Autowired
    private SeckillOrderService seckillOrderService;

    /**
     * 抢单-非热门
     */
    public RespResult add(String username,String id,String num){
        return RespResult.ok("抢单成功");
    }
}
