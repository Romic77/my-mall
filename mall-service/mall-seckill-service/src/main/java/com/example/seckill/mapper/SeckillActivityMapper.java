package com.example.seckill.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.seckill.model.SeckillActivity;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: chenq
 * @Description:
 * @Date: Created in 2021-07-09 16:39
 */
public interface SeckillActivityMapper extends BaseMapper<SeckillActivity> {
    /**
     * 有效的活动列表
     */
    @Select("select * from seckill_activity where end_time > now() order by start_time asc  limit 5")
    List<SeckillActivity> validActivity();
}
