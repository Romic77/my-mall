package com.example.goods;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "brand")
public class Brand {
    // ID（主键）  @TableId:标记当前属性为主键列对应的属性
    @TableId(type = IdType.AUTO)
    private Integer id;
    // 品牌名字
    private String name;
    // 品牌图片
    private String image;
    // 品牌首字母
    private String initial;
    // 排序
    private Integer sort;
}
