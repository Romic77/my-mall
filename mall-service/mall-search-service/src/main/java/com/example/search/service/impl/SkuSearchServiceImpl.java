package com.example.search.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.search.mapper.SkuSearchMapper;
import com.example.search.model.SkuEs;
import com.example.search.service.SkuSearchService;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SkuSearchServiceImpl implements SkuSearchService {
    @Autowired
    private SkuSearchMapper skuSearchMapper;

    /**
     * 根据关健词搜索
     *
     * @param searchMap
     * @return
     */
    @Override
    public Map<String, Object> search(Map<String, Object> searchMap) {
        //QueryBuilder ->构建搜索条件
        NativeSearchQueryBuilder queryBuilder = queryBuilder(searchMap);
        //skuSearchMapper进行搜索
        Page<SkuEs> page = skuSearchMapper.search(queryBuilder.build());
        //获取结果集：集合列表、总记录数
        Map<String, Object> resultMap = new HashMap<>();
        List<SkuEs> skuEsList = page.getContent();
        resultMap.put("totalElements", page.getTotalElements());
        resultMap.put("list", skuEsList);
        return resultMap;
    }

    /**
     * 搜索条件构建
     *
     * @param searchMap
     * @return
     */
    public NativeSearchQueryBuilder queryBuilder(Map<String, Object> searchMap) {
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        //判断关键词是否为空，不为空，则设置条件
        if (searchMap != null && searchMap.size() > 0) {
            //关键词条件
            String keywords = searchMap.get("keywords") + "";
            if (StringUtils.isNotEmpty(keywords)) {
                return builder.withQuery(QueryBuilders.termQuery("name", keywords.toString()));
            }

        }
        return null;
    }

    /**
     * 单个导入ES
     *
     * @param skuEs
     */
    @Override
    public void add(SkuEs skuEs) {
        //属性转换
        String skuAttr = skuEs.getSkuAttribute();
        if (!StringUtils.isEmpty(skuAttr)) {
            skuEs.setAttrMap(JSON.parseObject(skuAttr, Map.class));
        }
        skuSearchMapper.save(skuEs);
    }

    @Override
    public void del(String id) {
        skuSearchMapper.deleteById(id);
    }
}
