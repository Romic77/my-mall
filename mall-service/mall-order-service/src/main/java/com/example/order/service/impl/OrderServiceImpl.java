package com.example.order.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.feign.CartFeign;
import com.example.goods.feign.SkuFeign;
import com.example.model.Cart;
import com.example.order.mapper.OrderMapper;
import com.example.order.mapper.OrderSkuMapper;
import com.example.order.model.Order;
import com.example.order.model.OrderSku;
import com.example.order.service.OrderService;
import com.example.util.RespResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author: chenq
 * @Description:
 * @Date: Created in 2021-06-24 19:43
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private CartFeign cartFeign;

    @Autowired
    private SkuFeign skuFeign;

    @Autowired
    private OrderSkuMapper orderSkuMapper;

    @Autowired
    private OrderMapper orderMapper;
    /**
     * 添加订单
     *
     * @param order
     * @return
     */
    @Override
    public boolean add(Order order) {
        order.setId(IdWorker.getIdStr());
        order.setCreateTime(new Date());
        order.setUpdateTime(new Date());

        //1. 查询购物车数据
        RespResult<List<Cart>> result = cartFeign.getCartByIds(order.getCartIds());
        List<Cart> cartList = result.getData();
        if (cartList == null || cartList.size() == 0) {
            return false;
        }

        //2. 递减库存
        skuFeign.decount(cartList);

        //3. 添加订单明细
        int totalNum = 0;
        int moneys = 0;
        for (Cart cart : cartList) {
            OrderSku orderSku = JSON.parseObject(JSON.toJSONString(cart), OrderSku.class);
            orderSku.setId(IdWorker.getIdStr());
            orderSku.setOrderId(order.getId());
            orderSku.setMoney(orderSku.getNum() * orderSku.getPrice());
            //添加
            orderSkuMapper.insert(orderSku);

            totalNum += orderSku.getNum();
            moneys += orderSku.getMoney();
        }

        //4. 添加订单
        order.setTotalNum(totalNum);
        order.setMoneys(moneys);
        orderMapper.insert(order);
        return true;

    }
}
