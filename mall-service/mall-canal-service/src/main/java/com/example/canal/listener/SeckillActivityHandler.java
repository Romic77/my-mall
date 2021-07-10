package com.example.canal.listener;

import com.example.canal.job.DynamicJob;
import com.example.canal.job.DynamicTaskCreate;
import com.example.seckill.model.SeckillActivity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.javatool.canal.client.annotation.CanalTable;
import top.javatool.canal.client.handler.EntryHandler;

import java.text.SimpleDateFormat;

@Component
@CanalTable(value = "seckill_activity")
public class SeckillActivityHandler implements EntryHandler<SeckillActivity> {

    @Autowired
    private DynamicTaskCreate dynamicTaskCreate;

    /****
     * 增加活动
     * @param seckillActivity
     */
    @Override
    public void insert(SeckillActivity seckillActivity) {
        //创建任务调度，活动结束的时候执行
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ss mm HH dd MM ? yyyy");
        String cron = simpleDateFormat.format(seckillActivity.getEndTime());
        dynamicTaskCreate.create(seckillActivity.getId(), cron, 1, new DynamicJob(),seckillActivity.getId());
    }

    @Override
    public void update(SeckillActivity before, SeckillActivity after) {
        //创建任务调度，活动结束的时候执行
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ss mm HH dd MM ? yyyy");
        String cron = simpleDateFormat.format(after.getEndTime());
        dynamicTaskCreate.create(after.getId(), cron, 1, new DynamicJob(),after.getId());
    }

    @Override
    public void delete(SeckillActivity seckillActivity) {

    }
}