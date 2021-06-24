package com.example.order.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: chenq
 * @Description:
 * @Date: Created in 2021-06-24 18:59
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("order_sku")
public class OrderSku implements Serializable {
    @TableId(type = IdType.AUTO)
    private String id;
    private String image;
    private String skuId;
    private String orderId;
    private String name;
    private Integer price;
    private Integer num;
    private Integer money;
}
