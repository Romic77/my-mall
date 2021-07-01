package com.example.pay.service.impl;

import com.example.pay.service.WeiXinPayService;
import com.github.wxpay.sdk.WXPay;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * @Author: chenq
 * @Description:
 * @Date: Created in 2021-07-01 13:12
 */
public class WeiXinPayServiceImpl implements WeiXinPayService {
    @Autowired
    private WXPay wxPay;

    /***
     * 统一下单，获取支付二维码
     */
    @Override
    public Map<String, String> preOrder(Map<String, String> dataMap) throws Exception {
        Map<String, String> resp = wxPay.unifiedOrder(dataMap);
        return resp;
    }
}
