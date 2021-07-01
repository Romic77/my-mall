package com.example.order.controller;


import com.example.order.model.Order;
import com.example.order.pay.WeiXinPayParam;
import com.example.order.service.OrderService;
import com.example.util.RespResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: chenq
 * @Description:
 * @Date: Created in 2021-06-24 19:59
 */
@RestController
@RequestMapping("/order")
@CrossOrigin
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private WeiXinPayParam weiXinPayParam;

    /**
     * 添加订单
     */
    @PostMapping
    public RespResult add(@RequestBody Order order, HttpServletRequest request) throws Exception {
        //用户登陆名称
        order.setUsername("zs");
        //用户下单
        orderService.add(order);
        //扫码支付
        String ciptext =weiXinPayParam.weixinParam(order, request);
        return RespResult.ok(ciptext);
    }
}
