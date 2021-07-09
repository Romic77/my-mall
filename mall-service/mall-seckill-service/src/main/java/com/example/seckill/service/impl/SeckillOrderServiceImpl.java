package com.example.seckill.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.seckill.mapper.SeckillOrderMapper;
import com.example.seckill.model.SeckillOrder;
import com.example.seckill.service.SeckillOrderService;
import org.springframework.stereotype.Service;

/**
 * @Author: chenq
 * @Description:
 * @Date: Created in 2021-07-09 16:45
 */
@Service
public class SeckillOrderServiceImpl extends ServiceImpl<SeckillOrderMapper, SeckillOrder> implements SeckillOrderService {
}
