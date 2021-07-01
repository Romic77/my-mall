package com.example.pay.service;

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
}
