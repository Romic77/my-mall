package com.example.seckill.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

/**
 * @Author: chenq
 * @Description:
 * @Date: Created in 2021-07-09 16:29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("seckill_goods")
@Table
public class SeckillGoods {
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;
    @Column(name = "spu_id")
    private String spuId;
    @Column(name = "sku_id")
    private String skuId;
    private String name;
    private String images;
    private String content;
    private Integer price;
    @Column(name = "seckill_price")
    private Integer seckillPrice;
    private Integer num;
    @Column(name = "store_count")
    private Integer storeCount;
    @Column(name = "activity_id")
    private String activityId;
}
