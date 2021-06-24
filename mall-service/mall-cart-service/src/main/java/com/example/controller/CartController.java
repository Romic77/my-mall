package com.example.controller;

import com.example.model.Cart;
import com.example.service.CartService;
import com.example.util.RespResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: chenq
 * @Description:
 * @Date: Created in 2021-06-24 14:57
 */
@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    /**
     * 删除选中id集合的购物车数据
     */
    @DeleteMapping
    public RespResult deleteByIds(@RequestBody List<String> ids) {
        cartService.delete(ids);
        return RespResult.ok();
    }

    /**
     * 选中id集合的购物车数据
     */
    @PostMapping("/list")
    public RespResult<List<Cart>> getCartByIds(@RequestBody List<String> ids) {
        return RespResult.ok(cartService.findCartListById(ids));
    }

    /**
     * 添加到购物车
     *
     * @param id  商品id
     * @param num 商品数量
     */
    @PostMapping("/{id}/{num}")
    public void save(@PathVariable("id") String id, @PathVariable("num") Integer num) {
        cartService.save(id, "zs", num);
    }

    /**
     * 根据username查询购物车数据
     * @return
     */
    @GetMapping("/list")
    public RespResult<List<Cart>> list() {
        String userId = "";
        List<Cart> cartList = cartService.cartList(userId);
        return RespResult.ok(cartList);
    }
}
