package com.example.service;

import com.example.model.Cart;

import java.util.List;

/**
 * @Author: chenq
 * @Description:
 * @Date: Created in 2021-06-24 14:40
 */
public interface CartService {

    /**
     * 通过ids,查询mongodb指定购物车集合列表
     */
    List<Cart> findCartListById(List<String> idList);

    /**
     * 加入购物车
     */
    void save(String id,String userName,Integer num);

    /**
     * 根据集合删除指定的购物车列表
     * @param idList
     */
    void delete(List<String> idList);

    /**
     * 根据username查询购物车列表
     */
    List<Cart> cartList(String userName);
}
