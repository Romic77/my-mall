package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mapper.HotGoodsMapper;
import com.example.model.HotGoods;
import com.example.service.HotGoodsService;
import com.example.util.DruidPage;
import com.example.util.TimeUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: chenq
 * @Description:
 * @Date: Created in 2021-07-19 10:23
 */
@Service
public class HotGoodsServiceImpl extends ServiceImpl<HotGoodsMapper, HotGoods> implements HotGoodsService {
    @Autowired
    private HotGoodsMapper hotGoodsMapper;

    @Override
    public List<HotGoods> searchExclude(Integer size, Integer hour, String[] urls) {
        //urls拼接
        String urlString = StringUtils.join(urls, "','");
        hotGoodsMapper.searchExclude(size, TimeUtil.beforeTime(TimeUtil.unit_hour, hour), urlString);
        return null;
    }

    @Override
    public List<HotGoods> search(Integer size, Integer hour) {
        return hotGoodsMapper.search(size, TimeUtil.beforeTime(TimeUtil.unit_hour, hour));
    }

    @Override
    public List<HotGoods> topNum(Integer size) {
        return hotGoodsMapper.topNum(size);
    }

    @Override
    public DruidPage<List<HotGoods>> pageList(Integer page, Integer size) {
        //计算偏移量
        DruidPage<List<HotGoods>> druidPage = new DruidPage<List<HotGoods>>(page, size);

        //查询总数
        Integer totalCount = hotGoodsMapper.selectCount(null);

        //查询集合
        List<HotGoods> hotGoodsList = hotGoodsMapper.pageList(size, druidPage.getOffset());
        return druidPage.pages(hotGoodsList, totalCount);
    }

    @Override
    public DruidPage<List<HotGoods>> pageListSort(Integer page, Integer size, String sort, String sortType) {
        //计算偏移量
        DruidPage<List<HotGoods>> druidPage = new DruidPage<List<HotGoods>>(page, size, sort, sortType);

        //查询总数
        Integer totalCount = hotGoodsMapper.selectCount(null);

        //查询集合
        List<HotGoods> hotGoodsList = hotGoodsMapper.pageListSort(druidPage);
        return druidPage.pages(hotGoodsList, totalCount);
    }

}
