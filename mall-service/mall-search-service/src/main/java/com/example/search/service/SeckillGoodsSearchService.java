package com.example.search.service;

import com.example.search.model.SeckillGoodsEs;

/**
 * @Author: chenq
 * @Description:
 * @Date: Created in 2021-07-09 17:26
 */
public interface SeckillGoodsSearchService {
    /**
     * 秒杀商品导入es
     */
    void add(SeckillGoodsEs seckillGoodsEs);

    /**
     * 删除es商品
     * @param id
     */
    void del(String id);
}
