package com.example.seckill.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.seckill.mapper.SeckillActivityMapper;
import com.example.seckill.model.SeckillActivity;
import com.example.seckill.service.SeckillActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: chenq
 * @Description:
 * @Date: Created in 2021-07-09 16:46
 */
@Service
public class SeckillActivityServiceImpl extends ServiceImpl<SeckillActivityMapper, SeckillActivity> implements  SeckillActivityService{
    @Autowired
    private SeckillActivityMapper seckillActivityMapper;

    @Override
    public List<SeckillActivity> validActivity() {
        return seckillActivityMapper.validActivity();
    }
}
