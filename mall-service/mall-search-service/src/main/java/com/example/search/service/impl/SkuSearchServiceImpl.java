package com.example.search.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.search.mapper.SkuSearchMapper;
import com.example.search.model.SkuEs;
import com.example.search.service.SkuSearchService;
import com.example.search.util.HighlightResultMapper;
import com.example.util.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedStringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SkuSearchServiceImpl implements SkuSearchService {
    @Autowired
    private SkuSearchMapper skuSearchMapper;

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

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

        //1.设置高亮信息   关键词前（后）面的标签、设置高亮域
        HighlightBuilder.Field field = new HighlightBuilder
                .Field("name")  //根据指定的域进行高亮查询
                .preTags("<span style=\"color:red;\">")     //关键词高亮前缀
                .postTags("</span>")   //高亮关键词后缀
                .fragmentSize(100);     //碎片长度
        queryBuilder.withHighlightFields(field);

        //skuSearchMapper进行搜索
        //AggregatedPage<SkuEs> page = (AggregatedPage<SkuEs>) skuSearchMapper.search(queryBuilder.build());

        //2.将非高亮数据替换成高亮数据
        AggregatedPage<SkuEs> page = elasticsearchRestTemplate.queryForPage(queryBuilder.build(), SkuEs.class, new HighlightResultMapper());

        //获取结果集：集合列表、总记录数
        Map<String, Object> resultMap = new HashMap<>();
        //解析分组结果
        parseGroup(page.getAggregations(), resultMap);

        //动态属性解析
        attrParse(resultMap);

        List<SkuEs> skuEsList = page.getContent();

        //创建分页对象
        int currentPage = queryBuilder.build().getPageable().getPageNumber() + 1;
        PageInfo pageInfo = new PageInfo(page.getTotalElements(), currentPage, 5);
        resultMap.put("pageInfo", pageInfo);
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

        //前端没有传入属性参数的时候查询属性集合作为搜索条件
        String skuAttr = searchMap.get("skuAttribute") == null ? "" : searchMap.get("skuAttribute").toString();
        if (StringUtils.isEmpty(skuAttr)) {
            //品牌分组
            builder.addAggregation(AggregationBuilders
                    .terms("attrmaps")     //查询的数据对应别名
                    .field("skuAttribute")            //根据brandName分组
                    .size(100));                    //分组查询100条
        }
    }

    /**
     * 将属性信息合并成Map对象
     * 原始数据：[{"就业薪资":"10K起","学习费用":"2万"},{"就业薪资":"11K起","学习费用":"3万"}]
     * 最终数据：attrmaps:{"就业薪资":["10K起","11K起"],"学习费用":["2万","3万"]}
     */
    public void attrParse(Map<String, Object> resultMap) {
        //先获取attrmaps
        Object attrmaps = resultMap.get("attrmaps");
        if (attrmaps != null) {
            List<String> groupList = (List<String>) attrmaps;
            //定义一个集合Map<String,Set<String>>
            Map<String, Set<String>> allMaps = new HashMap<>();
            //循环集合
            for (String attr : groupList) {
                Map<String, String> attrMap = JSON.parseObject(attr, Map.class);
                for (Map.Entry<String, String> entry : attrMap.entrySet()) {
                    //获取每条记录，将记录转成Map
                    String key = entry.getKey();
                    Set<String> values = allMaps.get(key);
                    if (values == null) {
                        values = new HashSet<>();
                    }
                    values.add(entry.getValue());
                    //覆盖之前的数据
                    allMaps.put(key, values);
                }
            }
            //覆盖之前的attrmaps
            resultMap.put("attrmaps", allMaps);
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

        //组合查询对象
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        //判断关键词是否为空，不为空，则设置条件
        if (searchMap != null && searchMap.size() > 0) {
            //关键词条件
            String keywords = searchMap.get("keywords") == null ? "" : searchMap.get("keywords") + "";
            if (StringUtils.isNotEmpty(keywords)) {
                //return builder.withQuery(QueryBuilders.termQuery("name", keywords));
                boolQueryBuilder.must(QueryBuilders.termQuery("name", keywords));
            }

            //分类查询
            String category = searchMap.get("category") == null ? "" : searchMap.get("category") + "";
            if (StringUtils.isNotEmpty(category)) {
                boolQueryBuilder.must(QueryBuilders.termQuery("categoryName", category));
            }

            //品牌查询
            String brand = searchMap.get("brand") == null ? "" : searchMap.get("brand") + "";
            if (StringUtils.isNotEmpty(brand)) {
                boolQueryBuilder.must(QueryBuilders.termQuery("brandName", brand));
            }

            //价格区间查询
            // 查询 price=0-500元  500-1000  1000元以上
            //按照-分隔，要么就是：prices=[0,500]或者[500,1000]或者[1000]
            //所以分析  0<price<=500或者 500<price<=1000 或者 price>1000
            String price = searchMap.get("price") == null ? "" : searchMap.get("price") + "";
            if (StringUtils.isNotEmpty(price)) {
                //价格区间
                String[] prices = price.replace("元", "").replace("以上", "").split("-");
                // price > prices[0]
                boolQueryBuilder.must(QueryBuilders.rangeQuery("price").gt(Integer.parseInt(prices[0])));
                // price <= prices[1]
                if (prices.length == 2) {
                    boolQueryBuilder.must(QueryBuilders.rangeQuery("price").lte(Integer.parseInt(prices[1])));
                }
            }

            // 动态属性查询
            // 因为有多个动态属性,比如:attr_学习费用,attr_就业薪资等,就需要遍历searchMap
            // 比如这种情况: http://localhost:8084/search?attr_学习费用=1万&attr_就业薪资=6K起
            for (Map.Entry<String, Object> entry : searchMap.entrySet()) {
                //以attr_开始，动态属性 -> 例如前端传递attr_学习费用=1万 查询
                if (entry.getKey().startsWith("attr_")) {
                    String key = "attrMap." + entry.getKey().replaceFirst("attr_", "") + ".keyword";
                    boolQueryBuilder.must(QueryBuilders.termQuery(key, entry.getValue().toString()));
                }
            }
            //排序
            String sfield = searchMap.get("sfield") == null ? "" : searchMap.get("sfield") + "";
            String sm = searchMap.get("sm") == null ? "" : searchMap.get("sm") + "";
            if (StringUtils.isNotEmpty(sfield) && StringUtils.isNotEmpty(sm)) {
                builder.withSort(SortBuilders.fieldSort(sfield).order(SortOrder.valueOf(sm.toUpperCase())));
            }
        }

        //分页查询
        builder.withPageable(PageRequest.of(currentPage(searchMap), pageSize(searchMap)));
        return builder.withQuery(boolQueryBuilder);
    }

    /**
     * 分页参数:当前页
     */
    public int currentPage(Map<String, Object> searchMap) {
        String page = searchMap.get("page") == null ? "" : searchMap.get("page") + "";
        if (StringUtils.isEmpty(page)) {
            return 0;
        }
        return Integer.parseInt(page) - 1;
    }

    /**
     * 分页参数:每页数量
     */
    public int pageSize(Map<String, Object> searchMap) {
        String pageSize = searchMap.get("pageSize") == null ? "" : searchMap.get("pageSize") + "";
        if (StringUtils.isEmpty(pageSize)) {
            return 20;
        }
        return Integer.parseInt(pageSize);
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
