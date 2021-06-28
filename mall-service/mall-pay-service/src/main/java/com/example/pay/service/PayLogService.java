package com.example.pay.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.pay.model.PayLog;

public interface PayLogService extends IService<PayLog> {
    void add(PayLog payLog);
}
