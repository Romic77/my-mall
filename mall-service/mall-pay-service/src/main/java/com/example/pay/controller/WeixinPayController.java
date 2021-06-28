package com.example.pay.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.example.pay.model.PayLog;
import com.example.util.RespResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/wx")
@CrossOrigin
public class WeixinPayController {
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    /**
     * 支付结果回调
     */
    @GetMapping("/result")
    public RespResult result() {
        //创建日志对象
        PayLog payLog = new PayLog(IdWorker.getIdStr(), 1, "hello!", "aaa", new Date());

        //构建消息
        Message<String> message= MessageBuilder.withPayload(JSON.toJSONString(payLog)).build();

        //发消息
        //rocketMQTemplate.sendMessageInTransaction("rocket", "log", message,null );
        return RespResult.ok();
    }
}
