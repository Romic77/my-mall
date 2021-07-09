package com.example.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.goods.feign.CategoryFeign;
import com.example.goods.feign.SpuFeign;
import com.example.goods.model.Category;
import com.example.goods.model.Product;
import com.example.goods.model.Sku;
import com.example.goods.model.Spu;
import com.example.seckill.feign.SeckillGoodsFeign;
import com.example.seckill.model.SeckillGoods;
import com.example.service.PageService;
import com.example.service.SeckillPageService;
import com.example.util.RespResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: chenq
 * @Description:
 * @Date: Created in 2021-06-23 13:39
 */
@Service
public class SeckillPageServiceImpl implements SeckillPageService {
    @Autowired
    private SeckillGoodsFeign seckillGoodsFeign;

    @Value("${seckillpath}")
    private String seckillpath;

    @Autowired
    private TemplateEngine templateEngine;

    @Override
    public void html(String id) throws Exception {
        //1. 创建容器对象
        Context context = new Context();
        //2. 设置模板数据
        context.setVariables(loadData(id));
        //3. 指定文件生成后存储路径
        File file = new File(seckillpath, id + ".html");
        PrintWriter writer = new PrintWriter(file, "UTF-8");
        //4. 生成文件
        templateEngine.process("seckillitem", context, writer);
    }

    /**
     * 数据加载
     */
    public Map<String, Object> loadData(String id) {
        RespResult<SeckillGoods> goodsResp=seckillGoodsFeign.one(id);
        if(goodsResp.getData()!=null){
            Map<String, Object> dataMap=new HashMap<>();
            dataMap.put("item",goodsResp.getData());
            dataMap.put("images",goodsResp.getData().getImages().split(","));
            return dataMap;
        }
        return null;
    }
}
