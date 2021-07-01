package com.example.order.mq;


import com.alibaba.fastjson.JSON;
import com.example.order.service.OrderService;
import com.example.pay.model.PayLog;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.List;

@Component
@RocketMQMessageListener(topic = "log", consumerGroup = "orderGroup")
public class OrderResultListener implements RocketMQListener, RocketMQPushConsumerLifecycleListener {
    @Autowired
    private OrderService orderService;

    @Override
    public void onMessage(Object o) {

    }

    @Override
    public void prepareStart(DefaultMQPushConsumer defaultMQPushConsumer) {
        defaultMQPushConsumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                for (MessageExt messageExt : list) {
                    try {
                        String result = new String((byte[]) messageExt.getBody(), "UTF-8");
                        PayLog payLog = JSON.parseObject(result, PayLog.class);
                        if (payLog.getStatus().intValue() == 2) {
                            //支付成功
                            orderService.updateAfterPayStatus(payLog.getPayId());
                        }else{
                            //支付失败
                            //修改订单状态
                            //库存回滚
                        }


                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
                return null;
            }
        });
    }
}
