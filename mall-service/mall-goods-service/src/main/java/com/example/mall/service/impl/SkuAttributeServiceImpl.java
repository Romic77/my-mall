package com.example.mall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.goods.model.SkuAttribute;
import com.example.mall.mapper.SkuAttributeMapper;
import com.example.mall.service.SkuAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: chenq
 * @Description:
 * @Date: Created in 2021-06-10 10:37
 */
@Service
public class SkuAttributeServiceImpl extends ServiceImpl<SkuAttributeMapper, SkuAttribute> implements SkuAttributeService {
    @Autowired
    private SkuAttributeMapper skuAttributeMapper;

    @Override
    public List<SkuAttribute> queryList(Integer id) {
        return skuAttributeMapper.queryByCategoryId(id);
    }
}
