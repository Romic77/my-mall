package com.example.pay.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.pay.mapper.PayLogMapper;
import com.example.pay.model.PayLog;
import com.example.pay.service.WeiXinPayService;
import com.github.wxpay.sdk.WXPay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: chenq
 * @Description:
 * @Date: Created in 2021-07-01 13:12
 */
@Service
public class WeiXinPayServiceImpl implements WeiXinPayService {
    @Autowired
    private WXPay wxPay;

    @Autowired
    private PayLogMapper payLogMapper;

    /***
     * 统一下单，获取支付二维码
     */
    @Override
    public Map<String, String> preOrder(Map<String, String> dataMap) throws Exception {
        Map<String, String> resp = wxPay.unifiedOrder(dataMap);
        return resp;
    }

    @Override
    public PayLog result(String outno) throws Exception {
        //先查询PayLog看是否有结果
        PayLog payLog = payLogMapper.selectById(outno);

        if (payLog == null) {
            payLog = new PayLog();
            //数据库中没有数据就查询微信支付服务
            Map<String, String> dataMap = new HashMap<>();
            dataMap.put("out_trade_no", outno);
            Map<String, String> resp = wxPay.orderQuery(dataMap);
            String state = resp.get("trade_state");
            int status = tradeState(state);
            if (status == 2 || status == 3 || status == 4 || status == 5 || status == 7) {
                payLog = new PayLog(outno, status, JSON.toJSONString(resp), outno, new Date());
                payLogMapper.insert(payLog);
            }
        }
        return payLog;
    }

    /**
     * 申请退款
     * @param dataMap
     * @return
     * @throws Exception
     */
    @Override
    public Map<String, String> refund(Map<String, String> dataMap) throws Exception {
        return wxPay.refund(dataMap);
    }

    /**
     * 支付状态
     */
    public int tradeState(String tradeState) {
        int state = 1;
        switch (tradeState) {
            //未支付
            case "NOTPAY":
                state = 1;
                break;
            //已支付
            case "SUCCESS":
                state = 2;
                break;
            //转入退款
            case "REFUND":
                state = 3;
                break;
            //已关闭
            case "CLOSED":
                state = 4;
                break;
            //已撤销
            case "REVOKED":
                state = 5;
                break;
            //用户支付中
            case "USERPAYING":
                state = 6;
                break;
            //支付失败
            case "PAYERROR":
                state = 7;
                break;
            default:
                state = 1;

        }
        return state;
    }

}
