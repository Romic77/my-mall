package com.example.search.service.impl;

import com.alibaba.fastjson.JSON;

import com.example.search.mapper.SkuSearchMapper;
import com.example.search.model.SkuEs;
import com.example.search.service.SkuSearchService;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedStringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

        //分组查询调用
        group(queryBuilder, searchMap);

        //skuSearchMapper进行搜索
        AggregatedPage<SkuEs> page = (AggregatedPage<SkuEs>) skuSearchMapper.search(queryBuilder.build());


        //获取结果集：集合列表、总记录数
        Map<String, Object> resultMap = new HashMap<>();
        //解析分组结果
        parseGroup(page.getAggregations(), resultMap);

        List<SkuEs> skuEsList = page.getContent();
        resultMap.put("totalElements", page.getTotalElements());
        resultMap.put("list", skuEsList);
        return resultMap;
    }

    /***
     * 分组结果解析
     */
    public void parseGroup(Aggregations aggregations, Map<String, Object> resultMap) {
        if (aggregations != null) {
            for (Aggregation aggregation : aggregations) {
                //强转ParsedStringTerms
                ParsedStringTerms terms = (ParsedStringTerms) aggregation;

                //循环结果集对象
                List<String> values = new ArrayList<String>();
                for (Terms.Bucket bucket : terms.getBuckets()) {
                    values.add(bucket.getKeyAsString());
                }
                //名字
                String key = aggregation.getName();
                resultMap.put(key, values);
            }
        }
    }

    /**
     * 分组查询
     *
     * @return
     */
    public void group(NativeSearchQueryBuilder builder, Map<String, Object> searchMap) {
        //前端没有传入分类参数的时候查询分类集合作为搜索条件
        String category = searchMap.get("category") == null ? "" : searchMap.get("category").toString();
        if (StringUtils.isEmpty(category)) {
            //分类分组
            builder.addAggregation(AggregationBuilders
                    .terms("categoryList")  //查询的数据对应别名
                    .field("categoryName")         //根据categoryName分组
                    .size(100));                    //分组查询100条
        }
        //前端没有传入品牌参数的时候查询品牌集合作为搜索条件
        String brand = searchMap.get("brand") == null ? "" : searchMap.get("brand").toString();
        if (StringUtils.isEmpty(brand)) {
            //品牌分组
            builder.addAggregation(AggregationBuilders
                    .terms("brandList")     //查询的数据对应别名
                    .field("brandName")            //根据brandName分组
                    .size(100));                    //分组查询100条
        }
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
            String keywords = searchMap.get("keywords") == null ? "" : searchMap.get("keywords") + "";
            if (StringUtils.isNotEmpty(keywords)) {
                return builder.withQuery(QueryBuilders.termQuery("name", keywords));
            }

        }
        return builder;
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
