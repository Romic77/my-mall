package com.example.order.controller;


import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.example.order.model.Order;
import com.example.order.model.OrderRefund;
import com.example.order.pay.WeiXinPayParam;
import com.example.order.service.OrderService;
import com.example.util.RespResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

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

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

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
        String ciptext = weiXinPayParam.weixinParam(order, request);
        return RespResult.ok(ciptext);
    }

    /**
     * 申请取消订单(模拟测试退款订单)
     */
    @PutMapping("/refund/{id}")
    public RespResult refund(@PathVariable("id") String id, HttpServletRequest request) throws Exception {
        //查询订单,是否符合退款要求
        Order order = orderService.getById(id);
        if (order != null) {
            return RespResult.error("未查询到该订单id,操作错误");
        }
        //添加退款记录,更新订单状态
        if (order.getPayStatus().intValue() == 1 && order.getOrderStatus().intValue() == 1) {
            //添加退款记录,更新订单状态
            OrderRefund orderRefund = new OrderRefund(IdWorker.getIdStr(), order.getId(), 1,
                    null, order.getUsername(), 0, new Date(), order.getMoneys());
            orderService.refund(orderRefund);

            //向MQ发消息(申请退款)
            Message message = MessageBuilder.withPayload(weiXinPayParam.weixinRefundParam(orderRefund)).build();
            TransactionSendResult transactionSendResult = rocketMQTemplate.sendMessageInTransaction("refund", message, orderRefund);
            if (transactionSendResult.getSendStatus() == SendStatus.SEND_OK) {
                return RespResult.ok();
            }
        }


        //不符合直接返回错误
        return RespResult.error();
    }

}
