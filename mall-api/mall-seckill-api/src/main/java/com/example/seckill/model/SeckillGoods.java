package com.example.seckill.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class SeckillGoods {
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    private String spuId;
    private String skuId;
    private String name;
    private String images;
    private String content;
    private Integer price;
    private Integer seckillPrice;
    private Integer num;
    private Integer storeCount;
    private Date createTime;
    private String activityId;
}
