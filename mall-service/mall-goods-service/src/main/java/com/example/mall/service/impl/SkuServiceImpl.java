package com.example.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.goods.model.AdItems;
import com.example.goods.model.Sku;
import com.example.mall.mapper.AdItemsMapper;
import com.example.mall.mapper.SkuMapper;
import com.example.mall.service.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: chenq
 * @Description:
 * @Date: Created in 2021-06-17 11:19
 */
@Service
@CacheConfig(cacheNames = "ad-items-skus")
public class SkuServiceImpl extends ServiceImpl<SkuMapper, Sku> implements SkuService {

    @Autowired
    private AdItemsMapper adItemsMapper;

    @Autowired
    private SkuMapper skuMapper;

    /**
     * 根据推广产品分类id查询sku列表
     *
     * @param id
     * @return
     */
    @Override
    @Cacheable(key = "#id")
    public List<Sku> typeSkuItems(Integer id) {
        //1. 查询当前分类下的所有列表信息
        QueryWrapper<AdItems> adItemsQueryWrapper = new QueryWrapper<>();
        adItemsQueryWrapper.eq("type", id);
        List<AdItems> adItemsList = adItemsMapper.selectList(adItemsQueryWrapper);
        if (adItemsList == null || adItemsList.isEmpty()) {
            return null;
        }
        //2. 根据推广列表查询产品信息
        List<String> skuIdList = adItemsList.stream().map(AdItems::getSkuId).collect(Collectors.toList());
        return skuMapper.selectBatchIds(skuIdList);
    }

    @Override
    @CacheEvict(key = "#id")
    public void delTypeSkuItems(Integer id) {

    }

    @Override
    @CachePut(key = "#id")
    public List<Sku> updateTypeSkuItems(Integer id) {
        //1. 查询当前分类下的所有列表信息
        QueryWrapper<AdItems> adItemsQueryWrapper = new QueryWrapper<>();
        adItemsQueryWrapper.eq("type", id);
        List<AdItems> adItemsList = adItemsMapper.selectList(adItemsQueryWrapper);
        if (adItemsList == null || adItemsList.isEmpty()) {
            return null;
        }
        //2. 根据推广列表查询产品信息
        List<String> skuIdList = adItemsList.stream().map(AdItems::getSkuId).collect(Collectors.toList());
        return skuMapper.selectBatchIds(skuIdList);
    }
}
