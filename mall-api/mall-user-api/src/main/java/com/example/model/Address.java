package com.example.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: chenq
 * @Description:
 * @Date: Created in 2021-06-24 16:08
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("Address")
public class Address implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String username;
    private String provinceid;
    private String cityid;
    private String areaid;
    private String phone;
    private String address;
    private String contact;
    private String idDefault;
}
