package com.example.controller;

import com.example.model.HotGoods;
import com.example.service.HotGoodsService;
import com.example.util.DruidPage;
import com.example.util.RespResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: chenq
 * @Description:
 * @Date: Created in 2021-07-19 10:27
 */
@RestController
@RequestMapping("/hot/goods")
public class HotGoodsController {
    @Autowired
    private HotGoodsService hotGoodsService;

    /**
     * 查询所有
     */
    @GetMapping
    public RespResult<List<HotGoods>> list() {
        return RespResult.ok(hotGoodsService.list());
    }

    /**
     * 前N条记录
     *
     * @param size
     * @return
     */
    @GetMapping("/top/{size}")
    public RespResult<List<HotGoods>> topNum(@PathVariable("size") Integer size) {
        return RespResult.ok(hotGoodsService.topNum(size));
    }

    /**
     * 分页查询
     */
    @GetMapping("/page/{page}/{size}")
    public RespResult<DruidPage<List<HotGoods>>> pageList(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        return RespResult.ok(hotGoodsService.pageList(page, size));
    }

    /**
     * 分页查询+排序
     */
    @GetMapping("/page/{page}/{size}/{sort}/{sortType}")
    public RespResult<DruidPage<List<HotGoods>>> pageList(@PathVariable("page") Integer page, @PathVariable("size") Integer size,
                                                          @PathVariable("sort") String sort, @PathVariable("sortType") String sortType) {
        return RespResult.ok(hotGoodsService.pageListSort(page, size, sort, sortType));
    }

    /**
     * 时间查询
     */
    @GetMapping("/search/{size}/{hour}")
    public RespResult<List<HotGoods>> search(@PathVariable("size") Integer size, @PathVariable("hour") Integer hour) {

        return RespResult.ok(hotGoodsService.search(size, hour));
    }

    /**
     * 时间查询
     */
    @PostMapping("/search/{size}/{hour}")
    public RespResult<List<HotGoods>> searchExclude(@PathVariable("size") Integer size, @PathVariable("hour") Integer hour
            ,@RequestBody String[] urls) {

        return RespResult.ok(hotGoodsService.searchExclude(size, hour, urls));
    }
}
