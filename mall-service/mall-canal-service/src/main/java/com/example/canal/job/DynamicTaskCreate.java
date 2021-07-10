package com.example.canal.job;

import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DynamicTaskCreate {

    @Autowired
    private ZookeeperRegistryCenter zookeeperRegistryCenter;

    /***
     * 作业创建
     * @param jobName:作业名字
     * @param cron:表达式
     * @param shardingTotalCount:分片数量
     * @param instance:作业实例
     * @param parameters:额外参数
     */
    public void create(String jobName, String cron, int shardingTotalCount, SimpleJob instance, String parameters){

        //1.配置作业->Builder->构建：LiteJobConfiguration
        LiteJobConfiguration.Builder builder = LiteJobConfiguration.newBuilder(new SimpleJobConfiguration(
                JobCoreConfiguration.newBuilder(
                        jobName,
                        cron,
                        shardingTotalCount
                ).jobParameter(parameters).build(),
                instance.getClass().getName()
        )).overwrite(true);
        LiteJobConfiguration liteJobConfiguration = builder.build();

        //2.开启作业
        new SpringJobScheduler(instance,zookeeperRegistryCenter,liteJobConfiguration).init();
    }
}