package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.model.HotGoods;
import com.example.util.DruidPage;

import java.util.List;

/**
 * @Author: chenq
 * @Description:
 * @Date: Created in 2021-07-19 10:22
 */
public interface HotGoodsService extends IService<HotGoods> {

    List<HotGoods> topNum(Integer size);

    /**
     * 分页查询
     */
    DruidPage<List<HotGoods>> pageList(Integer page, Integer size);

    /**
     * 排序+ 分页查询
     */
    DruidPage<List<HotGoods>> pageListSort(Integer page, Integer size, String sort, String sortType);
}
