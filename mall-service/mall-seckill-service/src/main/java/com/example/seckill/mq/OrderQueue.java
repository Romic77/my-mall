package com.example.seckill.mq;

import com.alibaba.fastjson.JSON;
import com.example.seckill.service.SeckillOrderService;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author: chenq
 * @Description:
 * @Date: Created in 2021-07-22 09:13
 */
@RocketMQMessageListener(topic = "order-queue", consumerGroup = "order-consumer-group")
@Component
public class OrderQueue implements RocketMQListener {
    @Autowired
    private SeckillOrderService seckillOrderService;

    @Override
    public void onMessage(Object o) {
        seckillOrderService.add(JSON.parseObject(o.toString(), Map.class));
    }
}
