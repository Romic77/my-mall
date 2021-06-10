package com.example.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.goods.model.Brand;
import com.example.mall.mapper.BrandMapper;
import com.example.mall.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements BrandService {
    @Autowired
    private BrandMapper brandMapper;

    @Override
    public List<Brand> queryList(Brand brand) {
        QueryWrapper<Brand> queryWrapper = new QueryWrapper<Brand>();
        //根据name查询品牌
        queryWrapper.like("name", brand.getName());
        //根据initial查询
        queryWrapper.eq("initial", brand.getInitial());
        return brandMapper.selectList(queryWrapper);
    }

    @Override
    public Page<Brand> queryPageList(Brand brand, Long currentPage, Long size) {
        QueryWrapper<Brand> queryWrapper = new QueryWrapper<Brand>();
        //根据name查询品牌
        queryWrapper.like("name", brand.getName());
        return brandMapper.selectPage(new Page<Brand>(currentPage, size), queryWrapper);
    }

    @Override
    public List<Brand> queryByCategoryId(Integer id) {
        //根据分类id查询品牌集合
        List<Integer> brandIds = brandMapper.queryBrandIds(id);
        //根据品牌Id集合查询品牌信息
        if (brandIds != null && brandIds.size() > 0) {
            return brandMapper.selectList(new QueryWrapper<Brand>().in("id", brandIds));
        }
        return null;
    }
}
