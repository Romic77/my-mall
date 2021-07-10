package com.example.canal.listener;

import com.alibaba.fastjson.JSON;
import com.example.feign.SeckillPageFeign;
import com.example.search.feign.SeckillGoodsSearchFeign;
import com.example.search.model.SeckillGoodsEs;
import com.example.seckill.model.SeckillGoods;
import lombok.SneakyThrows;
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
@CanalTable("seckill_goods")
public class SeckillGoodsHandler implements EntryHandler<SeckillGoods> {
    @Autowired
    private SeckillGoodsSearchFeign seckillGoodsSearchFeign;

    @Autowired
    private SeckillPageFeign seckillPageFeign;

    /**
     * 数据库新增的时候,执行该方法
     *
     * @param seckillGoods
     */
    @SneakyThrows
    @Override
    public void insert(SeckillGoods seckillGoods) {
        //索引导入
        seckillGoodsSearchFeign.add(JSON.parseObject(JSON.toJSONString(seckillGoods), SeckillGoodsEs.class));
        //静态页生成
        seckillPageFeign.html(seckillGoods.getId());
    }

    /**
     * 数据库修改的时候,执行该方法
     */
    @SneakyThrows
    @Override
    public void update(SeckillGoods before, SeckillGoods after) {
        //索引导入
        seckillGoodsSearchFeign.add(JSON.parseObject(JSON.toJSONString(after), SeckillGoodsEs.class));

        //生成/更新静态页
        seckillPageFeign.html(after.getId());
    }

    /**
     * 数据库删除的时候,执行该方法
     *
     * @param seckillGoods
     */
    @Override
    public void delete(SeckillGoods seckillGoods) {

    }
}
