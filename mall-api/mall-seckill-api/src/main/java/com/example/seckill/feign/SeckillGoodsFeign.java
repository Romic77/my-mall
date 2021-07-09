package com.example.seckill.feign;

import com.example.seckill.model.SeckillGoods;
import com.example.util.RespResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("mall-seckill")
public interface SeckillGoodsFeign {
    /**
     * 根据id查询商品详情
     * @param id
     * @return
     */
    @GetMapping("/seckill/goods/{id}")
    RespResult<SeckillGoods> one(@PathVariable("id")String id);
}
