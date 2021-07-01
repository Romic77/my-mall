package com.example.pay.service;

import com.example.pay.model.PayLog;

import java.util.Map;

/**
 * @Author: chenq
 * @Description:
 * @Date: Created in 2021-07-01 13:12
 */
public interface WeiXinPayService {
    /***
     * 统一下单，获取支付二维码
     */
    Map<String,String> preOrder(Map<String,String> dataMap) throws Exception;

    /**
     * 主动查询支付结果
     * @param  outno 订单编号
     */
    PayLog result(String outno) throws Exception;

    /**
     * 退款申请
     */
    Map<String, String> refund(Map<String,String> dataMap) throws Exception;
}
