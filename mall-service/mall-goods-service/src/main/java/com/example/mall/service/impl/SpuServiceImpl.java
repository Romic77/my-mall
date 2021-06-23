package com.example.mall.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.goods.model.*;
import com.example.mall.mapper.BrandMapper;
import com.example.mall.mapper.CategoryMapper;
import com.example.mall.mapper.SkuMapper;
import com.example.mall.mapper.SpuMapper;
import com.example.mall.service.SpuService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author: chenq
 * @Description:
 * @Date: Created in 2021-06-10 13:24
 */
@Service
public class SpuServiceImpl extends ServiceImpl<SpuMapper, Spu> implements SpuService {
    @Autowired
    private SpuMapper spuMapper;
    @Autowired
    private SkuMapper skuMapper;
    @Autowired
    private BrandMapper brandMapper;
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public void saveProduct(Product product) {
        //1.保存spu
        Spu spu = product.getSpu();
        if (StringUtils.isEmpty(spu.getId())) {
            spu.setIsMarketable(1); //已上架
            spu.setIsDelete(0); //未删除
            spu.setStatus(1); //审核通过
            spuMapper.insert(spu);
        } else {
            //修改
            spuMapper.updateById(spu);
            //删除sku集合
            skuMapper.delete(new QueryWrapper<Sku>().eq("spu_id", spu.getId()));
        }
        //2.保存sku集合
        Date date = new Date();
        Category category = categoryMapper.selectById(spu.getCategoryThreeId());
        Brand brand = brandMapper.selectById(spu.getBrandId());
        for (Sku sku : product.getSkus()) {
            //sku名称
            String name = spu.getName();
            Map<String, String> skuAttrMap = JSON.parseObject(sku.getSkuAttribute(), Map.class);
            for (Map.Entry<String, String> entry : skuAttrMap.entrySet()) {
                name += " " + entry.getValue();
            }
            sku.setName(name);
            //创建时间
            sku.setCreateTime(date);
            //修改时间
            sku.setUpdateTime(date);
            //分类id
            sku.setCategoryId(spu.getCategoryThreeId());
            //分类名字
            sku.setCategoryName(category.getName());
            //品牌id
            sku.setBrandId(spu.getBrandId());
            //品牌名字
            sku.setBrandName(brand.getName());
            //spuid
            sku.setSpuId(spu.getId());
            //状态 商品状态 1-正常，2-下架，3-删除
            sku.setStatus(1);

            //添加
            skuMapper.insert(sku);
        }
    }

    /**
     * 根据spuid查询product
     *
     * @param id
     * @return
     */
    @Override
    public Product findBySpuId(String id) {
        //1. 查询spu
        Spu spu = spuMapper.selectById(id);
        //2. 查询sku集合
        QueryWrapper<Sku> queryWrapper = new QueryWrapper<Sku>();
        queryWrapper.eq("spu_id", id);
        List<Sku> skuList = skuMapper.selectList(queryWrapper);

        //product
        Product product = new Product();
        product.setSpu(spu);
        product.setSkus(skuList);
        return product;
    }
}
