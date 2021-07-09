package com.example.search.controller;

import com.example.search.model.SeckillGoodsEs;
import com.example.search.service.SeckillGoodsSearchService;
import com.example.util.RespResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: chenq
 * @Description:
 * @Date: Created in 2021-07-09 17:28
 */
@RestController
@RequestMapping("/seckill/goods")
public class SeckillGoodsController {
    @Autowired
    private SeckillGoodsSearchService seckillGoodsSearchService;

    /**
     * 将秒杀商品导入索引库
     */
    @PostMapping("/import")
    public RespResult add(@RequestBody SeckillGoodsEs seckillGoodsEs) {
        seckillGoodsSearchService.add(seckillGoodsEs);
        return RespResult.ok();
    }

    @DeleteMapping("/del/{id}")
    public RespResult del(@PathVariable("id")String id){
        seckillGoodsSearchService.del(id);
        return RespResult.ok();
    }
}
