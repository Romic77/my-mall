package com.example.order.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Author: chenq
 * @Description:
 * @Date: Created in 2021-06-24 17:55
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("order_info")
public class Order implements Serializable {
    @TableId(type = IdType.AUTO)
    private String id;
    private String payType;
    private Date createTime;
    private Date updateTime;
    private Date payTime;
    private Date consignTime;
    private Date endTime;
    private String username;
    /**
     * 收件人
     */
    private String recipients;
    private String recipientsMobile;
    private String recipientsAddress;
    private String weixinTransactionId;
    /**
     * 订单商品总数量
     */
    private Integer totalNum;
    /**
     * 支付总金额
     */
    private Integer moneys;
    private Integer orderStatus;
    private Integer payStatus;
    private Integer idDelete;

    //购物车Id集合
    @TableField(exist = false)
    private List<String> cartIds;
}
