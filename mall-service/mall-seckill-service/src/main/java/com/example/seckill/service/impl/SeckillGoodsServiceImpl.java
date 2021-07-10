package com.example.seckill.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.seckill.mapper.SeckillGoodsMapper;
import com.example.seckill.model.SeckillGoods;
import com.example.seckill.service.SeckillGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public SeckillGoods one(String id) {
        return seckillGoodsMapper.selectById(id);
    }

    @Override
    public List<SeckillGoods> actGoods(String acid) {
        QueryWrapper<SeckillGoods> seckillGoodsQueryWrapper = new QueryWrapper<SeckillGoods>();
        seckillGoodsQueryWrapper.eq("activity_id",acid);
        return seckillGoodsMapper.selectList(seckillGoodsQueryWrapper);
    }
}
