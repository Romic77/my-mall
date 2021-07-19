package com.example.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Author: chenq
 * @Description:
 * @Date: Created in 2021-07-19 10:04
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "msitemslog")
public class HotGoods {
    //ip
    private String ip;
    //访问的uri
    private String uri;
    //时间
    @TableField("__time")
    private Data accessTime;
}
