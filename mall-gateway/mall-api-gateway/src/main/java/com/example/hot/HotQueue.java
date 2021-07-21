package com.example.hot;

/**
 * @Author: chenq
 * @Description:
 * @Date: Created in 2021-07-21 16:56
 */

import com.alibaba.fastjson.JSON;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 排队操作
 */
@Component
public class HotQueue {

    @Autowired
    private RedisTemplate redisTempate;

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    public static final Integer NOT_HOT = 0;
    public static final Integer HAS_QUEUQ = 204;
    public static final Integer QUEUE_ING = 200;


    /**
     * 抢单排队
     * username: 用户名
     * id:商品id
     * num:件数
     */
    public int hot2Queue(String username, String id, Integer num) {
        //获取该商品在Redis里面的信息
        boolean bo = redisTempate.boundHashOps("HotSeckillGoods").hasKey(id);
        if (!bo) {
            return NOT_HOT;
        }

        //避免重复排队
        Long count = redisTempate.boundValueOps("OrderQueue" + username).increment(1);
        if (count > 1) {
            //请勿重复排队
            return HAS_QUEUQ;
        }


        //执行排队
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("username", username);
        dataMap.put("id", id);
        dataMap.put("num", num);

        Message<String> message = MessageBuilder.withPayload(JSON.toJSONString(dataMap)).build();
        rocketMQTemplate.convertAndSend("order-queue", message);
        return QUEUE_ING;
    }
}
