package com.example.canal.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.example.feign.SeckillPageFeign;

public class DynamicJob implements SimpleJob {

    //执行的作业
    @Override
    public void execute(ShardingContext shardingContext) {
        //静态页删除
        delete(shardingContext.getJobParameter());
    }

    /***
     * 执行静态页删除
     */
    public void delete(String acid){
        //从容器中获取指定的实例
        SeckillPageFeign seckillPageFeign = SpringContext.getBean(SeckillPageFeign.class);
        seckillPageFeign.deleByAct(acid);
    }
}