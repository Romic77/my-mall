package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.model.Permission;

import java.util.List;
import java.util.Map;

/**
 * @Author: chenq
 * @Description:
 * @Date: Created in 2021-07-30 09:47
 */
public interface PermissionService extends IService<Permission> {
    /**
     * @param urlMatch 传入1为通配符匹配 传入0为全量匹配
     */
    List<Permission> findByMatch(int urlMatch);

    /**
     * 所有角色的权限映射
     * @return
     */
    List<Map<Integer,Integer>> allRolePermissions();

}
