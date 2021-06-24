package com.example.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.goods.model.Sku;

import java.util.List;

/**
 * @Author: chenq
 * @Description:
 * @Date: Created in 2021-06-17 11:18
 */
public interface SkuService extends IService<Sku> {
    List<Sku> typeSkuItems(Integer id);

    void delTypeSkuItems(Integer id);

    List<Sku> updateTypeSkuItems(Integer id);
}
