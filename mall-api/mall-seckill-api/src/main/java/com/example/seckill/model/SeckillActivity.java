package com.example.seckill.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Table;
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
@Table
public class SeckillActivity {
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    @Column(name = "activity_name")
    private String activityName;
    private Integer type;
    @Column(name = "start_time")
    private Date startTime;
    @Column(name = "end_time")
    private Date endTime;
}
