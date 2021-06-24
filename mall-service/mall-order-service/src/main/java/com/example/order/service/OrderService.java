package com.example.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.order.model.Order;

/**
 * @Author: chenq
 * @Description:
 * @Date: Created in 2021-06-24 19:43
 */
public interface OrderService extends IService<Order> {
    boolean add(Order order);
}
