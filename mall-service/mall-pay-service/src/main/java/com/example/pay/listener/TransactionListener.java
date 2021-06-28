package com.example.pay.listener;

import com.alibaba.fastjson.JSON;
import com.example.pay.model.PayLog;
import com.example.pay.service.PayLogService;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
@RocketMQTransactionListener
public class TransactionListener implements RocketMQLocalTransactionListener {
    @Autowired
    private PayLogService payLogService;

    /**
     * half消息成功之后,执行本地事务
     *
     * @param message
     * @param o
     * @return
     */
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object o) {
        try {  //获得消息
            String result = new String((byte[]) message.getPayload(), "UTF-8");
            PayLog payLog = JSON.parseObject(result, PayLog.class);
            payLogService.add(payLog);
        } catch (Exception e) {
            e.printStackTrace();
            return RocketMQLocalTransactionState.ROLLBACK;
        }
        return RocketMQLocalTransactionState.COMMIT;
    }

    /**
     * 超时回查
     * @param message
     * @return
     */
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
        return RocketMQLocalTransactionState.COMMIT;
    }
}
