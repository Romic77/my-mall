package com.example.seckill.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author: chenq
 * @Description:
 * @Date: Created in 2021-07-09 16:25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("seckill_activity")
public class SeckillActivity {
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    private String activityName;
    private Integer type;
    private Date startTime;
    private Date endTime;
}
