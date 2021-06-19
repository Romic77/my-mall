package com.example.canal.listener;

import com.alibaba.fastjson.JSON;
import com.example.goods.model.Sku;
import com.example.search.feign.SkuSearchFeign;
import com.example.search.model.SkuEs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.javatool.canal.client.annotation.CanalTable;
import top.javatool.canal.client.handler.EntryHandler;

@CanalTable("sku")
@Component
public class SkuHandler implements EntryHandler<Sku> {
    @Autowired
    private SkuSearchFeign skuSearchFeign;

    /**
     * 增加数据监听
     *
     * @param sku
     */
    @Override
    public void insert(Sku sku) {
        if (sku.getStatus().intValue() == 1) {
            //将Sku转成json,再将json转为SkuEs
            skuSearchFeign.add(JSON.parseObject(JSON.toJSONString(sku), SkuEs.class));
        }
    }

    /**
     * 修改数据监听
     *
     * @param before
     * @param after
     */
    @Override
    public void update(Sku before, Sku after) {
        if (after.getStatus() == 2) { //下架就是删除索引
            //删除索引
            skuSearchFeign.del(after.getId());
        }else {
            skuSearchFeign.add(JSON.parseObject(JSON.toJSONString(after), SkuEs.class));
        }
    }

    /**
     * 删除数据监听
     * @param sku
     */
    @Override
    public void delete(Sku sku) {
        skuSearchFeign.del(sku.getId());

    }
}
