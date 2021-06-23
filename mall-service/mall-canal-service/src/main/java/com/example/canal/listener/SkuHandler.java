package com.example.canal.listener;

import com.alibaba.fastjson.JSON;
import com.example.feign.PageFeign;
import com.example.goods.model.Sku;
import com.example.search.feign.SkuSearchFeign;
import com.example.search.model.SkuEs;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.javatool.canal.client.annotation.CanalTable;
import top.javatool.canal.client.handler.EntryHandler;

@CanalTable("sku")
@Component
public class SkuHandler implements EntryHandler<Sku> {
    @Autowired
    private SkuSearchFeign skuSearchFeign;

    @Autowired
    private PageFeign pageFeign;

    /**
     * 增加数据监听
     *
     * @param sku
     */
    @SneakyThrows
    @Override
    public void insert(Sku sku) {
        if (sku.getStatus().intValue() == 1) {
            //将Sku转成json,再将json转为SkuEs
            skuSearchFeign.add(JSON.parseObject(JSON.toJSONString(sku), SkuEs.class));
        }
        //生成静态页
        pageFeign.html(sku.getSpuId());
    }

    /**
     * 修改数据监听
     *
     * @param before
     * @param after
     */
    @SneakyThrows
    @Override
    public void update(Sku before, Sku after) {
        if (after.getStatus() == 2) { //下架就是删除索引
            //删除索引
            skuSearchFeign.del(after.getId());
        }else {
            skuSearchFeign.add(JSON.parseObject(JSON.toJSONString(after), SkuEs.class));
        }
        pageFeign.html(after.getSpuId());

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
