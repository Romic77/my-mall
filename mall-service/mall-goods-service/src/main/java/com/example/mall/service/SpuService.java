package com.example.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.goods.model.Product;
import com.example.goods.model.Spu;

/**
 * @Author: chenq
 * @Description:
 * @Date: Created in 2021-06-10 13:23
 */
public interface SpuService extends IService<Spu> {

    /**
     * 产品保存
     * @param product
     */
    void saveProduct(Product product);

    Product findBySpuId(String id);
}
