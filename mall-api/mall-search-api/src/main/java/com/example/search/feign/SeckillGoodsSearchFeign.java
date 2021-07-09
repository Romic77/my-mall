package com.example.search.feign;

import com.example.search.model.SeckillGoodsEs;
import com.example.util.RespResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author: chenq
 * @Description:
 * @Date: Created in 2021-07-09 17:30
 */
@FeignClient("mall-search")
public interface SeckillGoodsSearchFeign {

    @PostMapping("/seckill/goods/import")
    public RespResult add(@RequestBody SeckillGoodsEs seckillGoodsEs);
}
