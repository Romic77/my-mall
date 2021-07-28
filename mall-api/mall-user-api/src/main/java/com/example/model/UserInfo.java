package com.example.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
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
    @TableId
    private String username;
    private String password;
    private String phone;
    private String email;
    private Date created;
    private Date updated;
    @Column(name = "source_type")
    private String sourceType;
    @Column(name = "nick_name")
    private String nickName;
    private String name;
    private String status;
    @Column(name = "head_pic")
    private String headPic;
    private String qq;
    @Column(name = "is_mobile_check")
    private String isMobileCheck;
    @Column(name = "is_email_check")
    private String isEmailCheck;
    private String sex;
    @Column(name = "user_level")
    private Integer userLevel;
    private Integer points;
    @Column(name = "experience_value")
    private Integer experienceValue;
    private Date birthday;
    @Column(name = "last_login_time")
    private Date lastLoginTime;
    private String provinceid;
    private String cityid;
    private String areaid;
    private String roles;
}
