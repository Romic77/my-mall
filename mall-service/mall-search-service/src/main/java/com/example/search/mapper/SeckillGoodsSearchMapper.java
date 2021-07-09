package com.example.search.mapper;

import com.example.search.model.SeckillGoodsEs;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @Author: chenq
 * @Description:
 * @Date: Created in 2021-07-09 17:25
 */
public interface SeckillGoodsSearchMapper extends ElasticsearchRepository<SeckillGoodsEs,String> {
}
