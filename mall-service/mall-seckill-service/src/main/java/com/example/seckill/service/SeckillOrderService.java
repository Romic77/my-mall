package com.example.seckill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.seckill.model.SeckillOrder;

import java.util.Map;

/**
 * @Author: chenq
 * @Description:
 * @Date: Created in 2021-07-09 16:45
 */
public interface SeckillOrderService extends IService<SeckillOrder> {
    public int add(Map<String,Object> dataMap);
}
