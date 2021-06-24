package com.example.mall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.goods.model.Sku;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * @Author: chenq
 * @Description:
 * @Date: Created in 2021-06-10 13:28
 */
public interface SkuMapper extends BaseMapper<Sku> {
    /**
     * 库存递减
     */
    @Update("update sku set num =num-#{num} where id =#{id} and num>=#{num}")
    int decrease(@Param("id") String id, @Param("num") Integer num);
}
