package com.example.search.service.impl;

import com.example.search.mapper.SeckillGoodsSearchMapper;
import com.example.search.model.SeckillGoodsEs;
import com.example.search.service.SeckillGoodsSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: chenq
 * @Description:
 * @Date: Created in 2021-07-09 17:26
 */
@Service
public class SeckillGoodsSearchServiceImpl implements SeckillGoodsSearchService {
    @Autowired
    private SeckillGoodsSearchMapper seckillGoodsSearchMapper;

    @Override
    public void add(SeckillGoodsEs seckillGoodsEs) {
        seckillGoodsSearchMapper.save(seckillGoodsEs);
    }

    @Override
    public void del(String id) {
        seckillGoodsSearchMapper.deleteById(id);
    }
}
