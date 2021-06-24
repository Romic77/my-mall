package com.example.service.impl;

import com.example.goods.feign.SkuFeign;
import com.example.goods.model.Sku;
import com.example.mapper.CartMapper;
import com.example.model.Cart;
import com.example.service.CartService;
import com.example.util.RespResult;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: chenq
 * @Description:
 * @Date: Created in 2021-06-24 14:41
 */
@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private SkuFeign skuFeign;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Cart> findCartListById(List<String> idList) {
        if (idList != null && !idList.isEmpty()) {
            //根据idList集合查询
            Iterable<Cart> carts = cartMapper.findAllById(idList);
            return Lists.newArrayList(carts);
        }
        return null;
    }

    /**
     * @param id       商品skuId
     * @param userName 用户登陆的名称类似noknok110
     * @param num      商品总数量
     */
    @Override
    public void save(String id, String userName, Integer num) {
        //1. 先删除当前id之前的购物车记录
        cartMapper.deleteById(userName + id);
        if (num > 0) {
            //2. 根据id查询Sku详情
            RespResult<Sku> result = skuFeign.findProductById(id);
            if (result != null && result.getData() != null) {
                //3. 将当前id商品对应的数据加入购物车(存入MongoDB)
                Sku sku = result.getData();
                Cart cart = new Cart();
                cart.set_id(userName + id);
                cart.setUserName(userName);
                cart.setName(sku.getName());
                cart.setImage(sku.getImage());
                cart.setPrice(sku.getPrice());
                cart.setSkuId(id);
                cart.setNum(num);
                cartMapper.save(cart);
            }
        }
    }

    @Override
    public void delete(List<String> idList) {
        mongoTemplate.remove(new Query(Criteria.where("_id").in(idList)), Cart.class);
    }

    /**
     * 根据userId查询购物车
     *
     * @param userId
     * @return
     */
    @Override
    public List<Cart> cartList(String userName) {
        //条件构造
        Cart cart = new Cart();
        cart.setUserName(userName);
        return cartMapper.findAll(Example.of(cart), Sort.by("_id"));
    }
}
