package com.example.search.service;

import com.example.search.model.SkuEs;

import java.util.Map;

public interface SkuSearchService {
    /**
     * 搜索数据
     * @param searchMap
     * @return
     */
    Map<String,Object> search(Map<String,Object> searchMap);

    //添加索引
    void add(SkuEs skuEs);

    //删除索引
    void del(String id);

}
