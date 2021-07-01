package com.example.pay.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.example.pay.model.PayLog;
import com.example.pay.service.WeiXinPayService;
import com.example.util.RespResult;
import com.example.util.Signature;
import com.github.wxpay.sdk.WXPayConstants;
import com.github.wxpay.sdk.WXPayUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/wx")
@CrossOrigin
public class WeixinPayController {
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Autowired
    private WeiXinPayService weiXinPayService;

    @Autowired
    private Signature signature;

    /**
     * 支付结果回调
     */
    @RequestMapping("/result")
    public String result(HttpServletRequest request) throws Exception {
        //获取request中的数据
        ServletInputStream is = request.getInputStream();
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = is.read(buffer)) != -1) {
            os.write(buffer, 0, len);
        }
        os.close();
        is.close();

        //将支付结果xml结果转成Map结构
        String xmlResult = new String(os.toByteArray(), "UTF-8");
        Map<String, String> map = WXPayUtil.xmlToMap(xmlResult);

        //判断支付结果状态 2成功,7失败
        int status = 7;
        if (StringUtils.equals(map.get("return_code"), WXPayConstants.SUCCESS)
                && StringUtils.equals(map.get("result_code"), WXPayConstants.SUCCESS)) {
            status = 2;
        }

        //创建日志对象
        PayLog payLog = new PayLog(IdWorker.getIdStr(), status, JSON.toJSONString(map), map.get("out_trade_no"), new Date());

        //构建消息
        Message<String> message = MessageBuilder.withPayload(JSON.toJSONString(payLog)).build();

        //发消息
        rocketMQTemplate.sendMessageInTransaction("log", message, null);

        //响应给微信服务器的数据
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("return_code","SUCCESS");
        resultMap.put("return_msg","OK");
        return WXPayUtil.mapToXml(resultMap);
    }

    /**
     * 申请退款状态
     */
    @RequestMapping("/refund/result")
    public String refund(HttpServletRequest request) throws Exception {
        //获取request中的数据
        ServletInputStream is = request.getInputStream();
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = is.read(buffer)) != -1) {
            os.write(buffer, 0, len);
        }
        os.close();
        is.close();

        //将支付结果xml结果转成Map结构
        String xmlResult = new String(os.toByteArray(), "UTF-8");
        Map<String, String> map = WXPayUtil.xmlToMap(xmlResult);

        //判断支付结果状态 2成功,7失败
        int status = 7;
        if (StringUtils.equals(map.get("return_code"), WXPayConstants.SUCCESS)
                && StringUtils.equals(map.get("result_code"), WXPayConstants.SUCCESS)) {
            status = 2;
        }

        //创建日志对象
        PayLog payLog = new PayLog(IdWorker.getIdStr(), status, JSON.toJSONString(map), map.get("out_trade_no"), new Date());

        //构建消息
        Message<String> message = MessageBuilder.withPayload(JSON.toJSONString(payLog)).build();

        //发消息
        rocketMQTemplate.sendMessageInTransaction("log", message, null);

        //响应给微信服务器的数据
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("return_code","SUCCESS");
        resultMap.put("return_msg","OK");
        return WXPayUtil.mapToXml(resultMap);
    }

    /*****
     * 预下单
     */
    @GetMapping(value = "/pay")
    public RespResult<Map> pay(@RequestParam("ciptext") String ciptext) throws Exception {
        //ciptext->AES->移除签名数据->md5 签名是否一致
        Map<String, String> map = signature.security(ciptext);

        //1分钱测试
        if (map != null) {
            Map<String, String> resultMap = weiXinPayService.preOrder(map);
            resultMap.put("orderNumber", map.get("out_trade_no"));
            resultMap.put("money", map.get("total_fee"));
            return RespResult.ok(resultMap);
        }
        return RespResult.error("支付系统繁忙，请稍后再试!");
    }

    /**
     * 查询订单支付状态
     */
    @GetMapping("/result/{outno}")
    public RespResult<PayLog> result(@PathVariable("outno") String outno) throws Exception {
        return RespResult.ok(weiXinPayService.result(outno));
    }
}
