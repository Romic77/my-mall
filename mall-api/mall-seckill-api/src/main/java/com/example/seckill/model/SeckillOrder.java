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
 * @Date: Created in 2021-07-09 16:32
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("seckill_order")
public class SeckillOrder {
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    private String seckillGoodsId;
    private String weixinTransactionId;
    private String username;
    private Integer money;
    private Integer status;
    private Date createTime;
    private Date payTime;
    private Integer num;
}
