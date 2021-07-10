package com.example.seckill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.seckill.model.SeckillGoods;

import java.util.List;

/**
 * @Author: chenq
 * @Description:
 * @Date: Created in 2021-07-09 16:44
 */
public interface SeckillGoodsService extends IService<SeckillGoods> {
    SeckillGoods one(String id);

    //根据活动ID查询商品信息
    List<SeckillGoods> actGoods(String acid);
}
