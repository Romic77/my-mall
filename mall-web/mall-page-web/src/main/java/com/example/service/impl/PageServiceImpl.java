package com.example.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.goods.feign.CategoryFeign;
import com.example.goods.feign.SpuFeign;
import com.example.goods.model.Category;
import com.example.goods.model.Product;
import com.example.goods.model.Sku;
import com.example.goods.model.Spu;
import com.example.service.PageService;
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
public class PageServiceImpl implements PageService {
    @Autowired
    private CategoryFeign categoryFeign;

    @Autowired
    private SpuFeign spuFeign;

    @Value("${pagepath}")
    private String pagepath;

    @Autowired
    private TemplateEngine templateEngine;

    @Override
    public void html(String spuId) throws Exception {
        //1. 创建容器对象
        Context context = new Context();
        //2. 设置模板数据
        context.setVariables(loadData(spuId));
        //3. 指定文件生成后存储路径
        File file = new File(pagepath, spuId + ".html");
        PrintWriter writer = new PrintWriter(file, "UTF-8");
        //4. 生成文件
        templateEngine.process("item", context, writer);
    }

    /**
     * 数据加载
     */
    public Map<String, Object> loadData(String spuId) {
        // 查询数据
        RespResult<Product> productResult = spuFeign.one(spuId);
        Product product = productResult.getData();
        if (product != null) {
            Map<String, Object> resultMap = new HashMap<String, Object>();
            //spu
            Spu spu = product.getSpu();
            resultMap.put("spu", spu);
            //图片按,分隔处理
            resultMap.put("images", spu.getImages().split(","));
            //属性列表
            resultMap.put("attrs", JSON.parseObject(spu.getAttributeList(), Map.class));
            //三级分类
            RespResult<Category> one = categoryFeign.one(spu.getCategoryOneId());
            RespResult<Category> two = categoryFeign.one(spu.getCategoryTwoId());
            RespResult<Category> three = categoryFeign.one(spu.getCategoryThreeId());
            resultMap.put("one", one.getData());
            resultMap.put("two", two.getData());
            resultMap.put("three", three.getData());

            //sku集合
            List<Map<String, Object>> skuList = new ArrayList<Map<String, Object>>();
            for (Sku sku : product.getSkus()) {
                Map<String, Object> skuMap = new HashMap<String, Object>();
                skuMap.put("id", sku.getId());
                skuMap.put("price", sku.getPrice());
                skuMap.put("name", sku.getName());
                skuMap.put("attr", sku.getSkuAttribute());
                skuList.add(skuMap);
            }
            resultMap.put("skuList", skuList);
            return resultMap;
        }

        return null;
    }
}
