package com.example.canal.listener;

import com.example.goods.feign.SkuFeign;
import com.example.goods.model.AdItems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.javatool.canal.client.annotation.CanalTable;
import top.javatool.canal.client.handler.EntryHandler;

/**
 * @Author: chenq
 * @Description:
 * @Date: Created in 2021-06-18 13:14
 */
@Component
@CanalTable("ad_items")
public class AdItemsHandler implements EntryHandler<AdItems> {
    @Autowired
    private SkuFeign skuFeign;

    /**
     * 数据库新增的时候,执行该方法
     *
     * @param adItems
     */
    @Override
    public void insert(AdItems adItems) {
        //重新加载缓存
        skuFeign.updateTypeItems(adItems.getType());
    }

    /**
     * 数据库修改的时候,执行该方法
     *
     * @param adItems
     */
    @Override
    public void update(AdItems before, AdItems after) {
        //这里有问题 before只会传递过来修改的字段的值,比如我修改的是AdItems.name的值
        //这里before.getType().intValue()就会空指针.蛋疼
        if (before.getType().intValue() != after.getType().intValue()) {
            //重新加载缓存
            skuFeign.updateTypeItems(before.getType());
        }
        //重新加载缓存
        skuFeign.updateTypeItems(after.getType());
    }

    /**
     * 数据库删除的时候,执行该方法
     *
     * @param adItems
     */
    @Override
    public void delete(AdItems adItems) {
        skuFeign.delTypeItems(adItems.getType());
    }
}
