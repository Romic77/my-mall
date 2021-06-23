package com.example.goods.feign;

import com.example.goods.model.Product;
import com.example.util.RespResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: chenq
 * @Description:
 * @Date: Created in 2021-06-23 13:37
 */
@FeignClient("mall-goods")
@RequestMapping("/spu")
public interface SpuFeign {

    @GetMapping("/{id}")
    public RespResult<Product> one(@PathVariable("id")String id);
}
