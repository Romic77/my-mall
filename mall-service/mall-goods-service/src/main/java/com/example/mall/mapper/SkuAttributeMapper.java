package com.example.mall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.goods.model.SkuAttribute;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: chenq
 * @Description:
 * @Date: Created in 2021-06-10 10:36
 */
public interface SkuAttributeMapper extends BaseMapper<SkuAttribute> {

    @Select("select * from sku_attribute where id  in (select attr_id from category_attr where category_id=#{id})")
    List<SkuAttribute> queryByCategoryId(Integer id);
}
