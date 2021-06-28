package com.example.pay.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.pay.mapper.PayLogMapper;
import com.example.pay.model.PayLog;
import com.example.pay.service.PayLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PayLogServiceImpl extends ServiceImpl<PayLogMapper, PayLog> implements PayLogService {
    @Autowired
    private PayLogMapper payLogMapper;

    @Override
    public void add(PayLog payLog) {
        payLogMapper.insert(payLog);
    }
}
