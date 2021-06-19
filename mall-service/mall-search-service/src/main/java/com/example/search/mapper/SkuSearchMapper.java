package com.example.search.mapper;

import com.example.search.model.SkuEs;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface SkuSearchMapper extends ElasticsearchRepository<SkuEs,String> {
}
