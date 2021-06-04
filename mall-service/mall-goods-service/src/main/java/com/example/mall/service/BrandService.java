package com.example.mall.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.goods.model.Brand;

import java.util.List;

public interface BrandService extends IService<Brand> {
    /**
     * 条件查询
     * @param brand
     * @return java.util.List<com.example.goods.model.Brand>
     * @author chenqi
     * @date 2021/6/5 0:19
    */
    List<Brand> queryList(Brand brand);
    
    /**
     * 条件分页查询
     * @param brand
     * @param currentPage
     * @param size
     * @return java.util.List<com.example.goods.model.Brand>
     * @author chenqi
     * @date 2021/6/5 0:31
    */
    Page<Brand> queryPageList(Brand brand, Long currentPage, Long size);
}
