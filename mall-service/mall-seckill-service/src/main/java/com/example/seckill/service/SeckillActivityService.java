package com.example.seckill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.seckill.model.SeckillActivity;

import java.util.List;

/**
 * @Author: chenq
 * @Description:
 * @Date: Created in 2021-07-09 16:43
 */
public interface SeckillActivityService extends IService<SeckillActivity> {
    /**
     * 有效的活动列表
     */
    List<SeckillActivity> validActivity();

}
