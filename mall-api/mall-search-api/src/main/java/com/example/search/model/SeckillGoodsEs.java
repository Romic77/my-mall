package com.example.search.model;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author: chenq
 * @Description:
 * @Date: Created in 2021-07-09 17:20
 */
@Data
@Document(indexName = "shopseckill", type = "seckillgoodses")
public class SeckillGoodsEs implements Serializable {
    @Id
    private String id;
    private String spuId;
    private String skuId;
    @Field(type = FieldType.Text, searchAnalyzer = "ik_smart", analyzer = "ik_smart")
    private String name;
    private String images;
    private Integer price;
    private Integer seckillPrice;
    private Integer num;
    private Integer storeCount;
    private Date createTime;
    @Field(type = FieldType.Keyword)
    private String activityId;

}
