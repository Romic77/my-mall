package com.example.feign;

import com.example.model.Cart;
import com.example.util.RespResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: chenq
 * @Description:
 * @Date: Created in 2021-06-24 16:52
 */
@FeignClient("mall-cart")
public interface CartFeign {
    /**
     * 删除选中id集合的购物车数据
     */
    @DeleteMapping("/cart")
    public RespResult deleteByIds(@RequestBody List<String> ids) ;


    /**
     * 选中id集合的购物车数据
     */
    @GetMapping("/cart/list")
    public RespResult<List<Cart>> getCartByIds(@RequestBody List<String> ids) ;
}
