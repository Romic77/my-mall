package com.example.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: chenq
 * @Description:
 * @Date: Created in 2021-06-24 16:08
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("user_info")
public class UserInfo implements Serializable {
    private String username;
    private String password;
    private String phone;
    private String email;
    private Date created;
    private Date updated;
    private String sourceType;
    private String nikeName;
    private String name;
    private String status;
    private String headPic;
    private String qq;
    private String isMobileCheck;
    private String isEmailCheck;
    private String sex;
    private Integer userLevel;
    private Integer points;
    private Integer experienceValue;
    private Date birthday;
    private Date lastLoginTime;
    private String provinceid;
    private String cityid;
    private String areaid;
    private String roles;
}
