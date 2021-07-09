package com.example.seckill.controller;

import com.example.seckill.model.SeckillGoods;
import com.example.seckill.service.SeckillGoodsService;
import com.example.util.RespResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: chenq
 * @Description:
 * @Date: Created in 2021-07-09 16:42
 */
@RestController
@RequestMapping("/seckill/goods")
public class SeckillGoodsController {
@Autowired
private SeckillGoodsService seckillGoodsService;

    @GetMapping("/seckill/goods/{id}")
    RespResult<SeckillGoods> one(@PathVariable("id")String id){
        return RespResult.ok(seckillGoodsService.one(id));
    }
}
