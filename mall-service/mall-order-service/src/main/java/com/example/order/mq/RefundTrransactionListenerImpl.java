package com.example.order.mq;

import com.example.order.mapper.OrderRefundMapper;
import com.example.order.model.OrderRefund;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
@RocketMQTransactionListener
public class RefundTrransactionListenerImpl implements RocketMQLocalTransactionListener {

    @Autowired
    private OrderRefundMapper orderRefundMapper;

    /****
     * 变更订单状态，记录退款申请信息
     * @param msg
     * @param arg
     * @return
     */
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        try {
            //退款申请记录
            OrderRefund orderRefund = (OrderRefund) arg;
            orderRefundMapper.deleteById(orderRefund.getId());
            int count = orderRefundMapper.insert(orderRefund);
            if(count<=0){
                return RocketMQLocalTransactionState.ROLLBACK;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return RocketMQLocalTransactionState.ROLLBACK;
        }
        return RocketMQLocalTransactionState.COMMIT;
    }


    /****
     * 消息回查
     * @param msg
     * @return
     */
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
        return RocketMQLocalTransactionState.COMMIT;
    }
}
