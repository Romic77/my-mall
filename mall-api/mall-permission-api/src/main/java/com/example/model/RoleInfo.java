package com.example.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
//MyBatisPlus表映射注解
@TableName(value = "role_info")
public class RoleInfo implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String roleName;
    private String description;
    //权限列表
    @TableField(exist = false)
    private List<Permission> permissions;
}