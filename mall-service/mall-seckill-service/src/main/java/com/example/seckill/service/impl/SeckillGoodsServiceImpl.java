package com.example.seckill.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.seckill.mapper.SeckillGoodsMapper;
import com.example.seckill.model.SeckillGoods;
import com.example.seckill.service.SeckillGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: chenq
 * @Description:
 * @Date: Created in 2021-07-09 16:46
 */
@Service
public class SeckillGoodsServiceImpl extends ServiceImpl<SeckillGoodsMapper, SeckillGoods> implements SeckillGoodsService {
    @Autowired
    private SeckillGoodsMapper seckillGoodsMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public SeckillGoods one(String id) {
        return seckillGoodsMapper.selectById(id);
    }

    @Override
    public List<SeckillGoods> actGoods(String acid) {
        QueryWrapper<SeckillGoods> seckillGoodsQueryWrapper = new QueryWrapper<SeckillGoods>();
        seckillGoodsQueryWrapper.eq("activity_id", acid);
        return seckillGoodsMapper.selectList(seckillGoodsQueryWrapper);
    }

    @Override
    public void isolation(String uri) {
        //1. 锁定商品
        QueryWrapper<SeckillGoods> seckillGoodsQueryWrapper = new QueryWrapper<>();
        seckillGoodsQueryWrapper.eq("id", uri);
        seckillGoodsQueryWrapper.eq("islock", 0);
        seckillGoodsQueryWrapper.gt("store_count", 0);
        SeckillGoods seckillGoods = new SeckillGoods();
        seckillGoods.setIslock(1);
        int count = seckillGoodsMapper.update(seckillGoods, seckillGoodsQueryWrapper);

        //2. 分离->查询出来存入Redis缓存

        //key: HotSeckillGoods value: uri:seckillGoods.getStoreCount()
        if (count > 0) {
            seckillGoods = seckillGoodsMapper.selectById(uri);
            redisTemplate.boundHashOps("HotSeckillGoods").increment(uri, seckillGoods.getStoreCount());
        }
    }
}
