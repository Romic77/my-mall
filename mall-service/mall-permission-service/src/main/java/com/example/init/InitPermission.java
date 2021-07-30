package com.example.init;

import com.example.model.Permission;
import com.example.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @Author: chenq
 * @Description:
 * @Date: Created in 2021-07-30 09:54
 */
@Component
public class InitPermission implements ApplicationRunner {
    @Autowired
    private PermissionService permissionService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //根据匹配方式查找
        List<Permission> permissionMatch0 = permissionService.findByMatch(0);
        List<Permission> permissionMatch1 = permissionService.findByMatch(1);

        //查询所有角色权限
        List<Map<Integer, Integer>> rolePermissions = permissionService.allRolePermissions();
        //角色权限处理
        Map<String, Set<Permission>> roleMap = rolePermissionFilter(rolePermissions, permissionMatch0, permissionMatch1);
        //将所有权限和角色存入Redis缓存
        redisTemplate.boundHashOps("RolePermissionAll").put("PermissionListMatch0", permissionMatch0);
        redisTemplate.boundHashOps("RolePermissionAll").put("PermissionListMatch1", permissionMatch1);
        redisTemplate.boundHashOps("RolePermissionMap").putAll(roleMap);
    }

    /**
     * @param rolePermissions  角色权限映射关系
     * @param permissionMatch0 所有完全匹配路径
     * @param permissionMatch1 所有通配符匹配路径
     * @return
     */
    public Map<String, Set<Permission>> rolePermissionFilter(List<Map<Integer, Integer>> rolePermissions,
                                                             List<Permission> permissionMatch0, List<Permission> permissionMatch1) {
        //角色权限关系key=roleId value= Set<Permission>
        Map<String, Set<Permission>> rolePermissionMapping = new HashMap<>();

        //根绝角色查找权限
        for (Map<Integer, Integer> rolePermissionMap : rolePermissions) {
            Integer rid = rolePermissionMap.get("rid");  //角色id
            Integer pid = rolePermissionMap.get("pid");  //权限id
            String key0 = "Role_0_" + rid.intValue();
            String key1 = "Role_1_" + rid.intValue();

            Set<Permission> permissionSet0 = rolePermissionMapping.get(key0);
            Set<Permission> permissionSet1 = rolePermissionMapping.get(key1);

            //防止空指针
            permissionSet0 = permissionSet0 == null ? new HashSet<Permission>() : permissionSet0;
            permissionSet1 = permissionSet1 == null ? new HashSet<Permission>() : permissionSet1;

            //循环匹配
            for (Permission permission : permissionMatch0) {
                if (permission.getId().intValue() == pid.intValue()) {
                    permissionSet0.add(permission);
                    break;
                }
            }

            for (Permission permission : permissionMatch1) {
                if (permission.getId().intValue() == pid.intValue()) {
                    permissionSet1.add(permission);
                    break;
                }
            }
            //将数据添加到rolePermissionMapping中
            if (permissionSet0.size() > 0) {
                rolePermissionMapping.put(key0, permissionSet0);
            }
            if (permissionSet1.size() > 0) {
                rolePermissionMapping.put(key1, permissionSet1);
            }
        }
        return rolePermissionMapping;
    }

}
