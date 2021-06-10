package com.example.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.goods.model.Category;

import java.util.List;

/**
 * @Author: chenq
 * @Description:
 * @Date: Created in 2021-06-10 09:30
 */
public interface CategoryService extends IService<Category> {

    /**
     * 根据分类父id查询所以子分类
     * @param pid
     * @return
     */
    List<Category> findByParentId(Integer pid);
}
