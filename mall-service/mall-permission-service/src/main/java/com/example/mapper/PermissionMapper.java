package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.model.Permission;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @Author: chenq
 * @Description:
 * @Date: Created in 2021-07-30 09:45
 */
public interface PermissionMapper extends BaseMapper<Permission> {
    @Select("SELECT * FROM role_permission")
    List<Map<Integer, Integer>> allRolePermissions();
}
