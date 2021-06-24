package com.example.order.controller;

import com.example.order.model.Order;
import com.example.order.service.OrderService;
import com.example.util.RespResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: chenq
 * @Description:
 * @Date: Created in 2021-06-24 19:59
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    /**
     * 添加订单
     */
    @PostMapping
    public RespResult add(@RequestBody Order order){
        order.setUsername("");
        orderService.add(order);
        return RespResult.ok();
    }
}
