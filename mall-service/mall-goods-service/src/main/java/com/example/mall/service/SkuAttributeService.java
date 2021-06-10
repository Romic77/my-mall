package com.example.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.goods.model.SkuAttribute;

import java.util.List;

/**
 * @Author: chenq
 * @Description:
 * @Date: Created in 2021-06-10 10:37
 */
public interface SkuAttributeService extends IService<SkuAttribute> {

    /**
     * 根据分类id查询属性集合
     */
    List<SkuAttribute> queryList(Integer id);
}
