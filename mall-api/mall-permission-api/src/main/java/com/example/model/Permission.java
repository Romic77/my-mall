package com.example.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
//MyBatisPlus表映射注解
@TableName(value = "permission")
public class Permission implements Serializable {

   @TableId(type = IdType.AUTO)
    private Integer id;
    private String sourceName;
    private String url;
    private Integer urlMatch;
    private String serviceName;
    private String method;
}