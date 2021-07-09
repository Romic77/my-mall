package com.example.seckill.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.seckill.mapper.SeckillGoodsMapper;
import com.example.seckill.model.SeckillGoods;
import com.example.seckill.service.SeckillGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
