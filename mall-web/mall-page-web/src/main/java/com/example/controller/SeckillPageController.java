package com.example.controller;

import com.example.seckill.feign.SeckillGoodsFeign;
import com.example.seckill.model.SeckillGoods;
import com.example.service.SeckillPageService;
import com.example.util.RespResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/page")
public class SeckillPageController {

    @Autowired
    private SeckillPageService seckillPageService;

    @Autowired
    private SeckillGoodsFeign seckillGoodsFeign;

    /**
     * 生成秒杀商品详情页
     */
    @GetMapping("/seckill/goods/{id}")
    public RespResult page(@PathVariable("id")String id) throws Exception {
        seckillPageService.html(id);
        return RespResult.ok();
    }
    /***
     * 删除指定活动的页面
     */
    @DeleteMapping(value = "/seckill/goods/{acid}")
    public RespResult deleByAct(@PathVariable("acid")String acid){
        //1.查询当前活动ID对应的商品列表数据\
        RespResult<List<SeckillGoods>> listRespResult = seckillGoodsFeign.actGoods(acid);
        List<SeckillGoods> goodsList = listRespResult.getData();
        //2.根据列表数据删除页面
        if(goodsList!=null){
            //循环删除页面
            for (SeckillGoods seckillGoods : goodsList) {
                seckillPageService.delete(seckillGoods.getId());
            }
        }
        return RespResult.ok();
    }

}
