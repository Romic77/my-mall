package com.example.pay.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.example.pay.model.PayLog;
import com.example.pay.service.WeiXinPayService;
import com.example.util.RespResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/wx")
@CrossOrigin
public class WeixinPayController {
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Autowired
    private WeiXinPayService weiXinPayService;

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
        rocketMQTemplate.sendMessageInTransaction("log",  message,null );
        return RespResult.ok();
    }

    /*****
     * 预下单
     */
    @GetMapping(value = "/pay")
    public RespResult<Map> pay(@RequestParam Map<String,String> map) throws Exception {
        //1分钱测试
        if(map!=null){
            Map<String, String> resultMap = weiXinPayService.preOrder(map);
            resultMap.put("orderNumber",map.get("out_trade_no"));
            resultMap.put("money",map.get("total_fee"));
            return RespResult.ok(resultMap);
        }
        return RespResult.error("支付系统繁忙，请稍后再试!");
    }
}
