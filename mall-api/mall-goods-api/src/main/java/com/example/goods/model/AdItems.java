package com.example.goods.model;

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
 * @Date: Created in 2021-06-17 11:14
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
//MyBatisPlus注解
@TableName("ad_items")
public class AdItems implements Serializable {
    @TableId(type= IdType.AUTO)
    private Integer id;
    private String name;
    private Integer type;
    private String skuId;
    private Integer sort;
}
