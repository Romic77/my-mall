package com.example.seckill.task;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dangdang.elasticjob.lite.annotation.ElasticSimpleJob;
import com.example.feign.HotGoodsFeign;
import com.example.seckill.service.SeckillGoodsService;
import com.example.util.RespResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author: chenq
 * @Description:
 * @Date: Created in 2021-07-21 14:09
 */
@ElasticSimpleJob(
        jobName = "${elasticjob.zookeeper.namespace}",
        shardingTotalCount = 1,
        cron = "0/10 * * * * ? *"
)
public class DiscoverHotGoods implements SimpleJob {
    @Autowired
    private HotGoodsFeign hotGoodsFeign;
    @Autowired
    private SeckillGoodsService seckillGoodsService;
    @Autowired
    private RedisTemplate redisTemplate;

    @Value("${hot.size}")
    private Integer size;

    @Value("${hot.hour}")
    private Integer hour;

    @Value("${hot.max}")
    private Integer max;

    //任务执行
    @Override
    public void execute(ShardingContext shardingContext) {
        //从Redis查询所有热门商品
        String[] urls = isolationList();

        RespResult<List<Map<String, String>>> listRespResult = hotGoodsFeign.searchHotGoods(size, hour, urls, max);
        List<Map<String, String>> data = listRespResult.getData();

        for (Map<String, String> dataMap : data) {
            //System.out.println(uriRaplace(1,dataMap.get("uri")));
            seckillGoodsService.isolation(uriRaplace(1, dataMap.get("uri")));
        }
    }

    /**
     * 查询已存在的所有热门商品id集合
     */
    public String[] isolationList() {
        Set<String> ids = redisTemplate.boundHashOps("HotSeckillGoods").keys();
        //转成字符串数组
        String[] allIds = new String[ids.size()];
        ids.toArray(allIds);
        return allIds;
    }

    /**
     * @param type 1 传入的是uri  2传入的商品id
     * @param uri
     * @return
     */
    public String uriRaplace(Integer type, String uri) {
        switch (type) {
            case 1:
                uri = uri.replace("/msitems/", "").replace(".html", "");
                break;
            case 2:
                uri = "/msitems/" + uri + ".html";
                break;
            default:
                uri = "/msitems/" + uri + ".html";
        }
        return uri;
    }
}
