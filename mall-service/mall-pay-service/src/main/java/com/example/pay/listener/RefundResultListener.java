package com.example.pay.listener;

import com.example.pay.service.WeiXinPayService;
import com.example.util.Signature;
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
import java.util.Map;

/**
 * @Author: chenq
 * @Description:
 * @Date: Created in 2021-07-01 16:34
 */
@Component
@RocketMQMessageListener(topic = "refund", consumerGroup = "refundGroup")
public class RefundResultListener implements RocketMQListener, RocketMQPushConsumerLifecycleListener {
    @Autowired
    private Signature signature;

    @Autowired
    private WeiXinPayService weiXinPayService;

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
                        //AES加密字符串
                        String result = new String((byte[]) messageExt.getBody(), "UTF-8");
                        Map<String, String> dataMap = signature.security(result);

                        //发送请求执行退款申请
                        Map<String, String> refundMap=weiXinPayService.refund(dataMap);

                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                return null;
            }
        });
    }
}
