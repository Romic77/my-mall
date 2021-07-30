package com.example.permission;

import com.example.model.Permission;
import com.example.util.IpUtil;
import com.example.util.JwtToken;
import com.example.util.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author: chenq
 * @Description:
 * @Date: Created in 2021-07-30 13:13
 */
@Component
public class AuthorizationIntterceptor {
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 判断是否需要拦截指定请求
     */
    public boolean isIntercept(ServerWebExchange exchange) {
        //request
        ServerHttpRequest request = exchange.getRequest();
        //uri
        String uri = request.getURI().toString();
        //提交方法
        String method = request.getMethodValue();
        //路由URI信息
        URI routerUri = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR);
        //获取所有权限
        List<Permission> permissionMatch0 = (List<Permission>) redisTemplate.boundHashOps("RolePermissionAll").get("PermissionListMatch0");
        //完全匹配
        Permission permission = match0(permissionMatch0, uri, method, routerUri.getHost());
        //完全匹配如果没有，则匹配通配符
        if (permission == null) {
            //匹配通配符
            List<Permission> permissionMatch1 = (List<Permission>) redisTemplate.boundHashOps("RolePermissionAll").get("PermissionListMatch1");
            //进行匹配，这里作为作业
            permission = match1(permissionMatch1, uri, method, routerUri.getHost());
        }
        //如果此时permission则表示不需要进行权限校验
        if (permission == null) {
            //不需要权限校验
            return false;
        }
        return true;

    }

    /***
     * 权限校验
     */
    public Boolean rolePermission(ServerWebExchange exchange, Map<String, Object> token) {
        ServerHttpRequest request = exchange.getRequest();
        //获取uri  /cart/list
        String uri = request.getURI().getPath();
        //提交方式  GET/POST/*
        String method = request.getMethodValue();
        //服务名字
        URI routerUri = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR);
        String servicename = routerUri.getHost();

        //匹配->获取角色集合
        String[] roles = token.get("roles").toString().split(",");

        Permission permission = null;
        //循环判断每个角色是否有权限
        for (String role : roles) {
            //获取完全匹配权限集合
            Set<Permission> permissions = (Set<Permission>) redisTemplate.boundHashOps("RolePermissionMap").get("Role_0_" + role);

            if (permissions == null) {
                continue;
            }
            //循环判断
            permission = match0(new ArrayList<Permission>(permissions), uri, method, servicename);
            if (permission != null) {
                break;
            }
        }

        //permission==null，通配符方式匹配
        if (permission == null) {
            //通配符匹配
        }
        return permission != null;
    }

    /***
     * 完全匹配
     * @param permissionMatch0
     * @param uri
     * @param method
     * @return
     */
    public Permission match0(List<Permission> permissionMatch0, String uri, String method, String serviceName) {
        //循环匹配
        for (Permission permission : permissionMatch0) {
            String matchUrl = permission.getUrl();
            String matchMethod = permission.getMethod();
            if (matchUrl.equals(uri)) {
                //匹配提交方式
                if (!matchMethod.equals("*") && matchMethod.equalsIgnoreCase(method) && serviceName.equals(permission.getServiceName())) {
                    return permission;
                }
            }
        }
        return null;
    }

    /**
     * 通配符匹配
     *
     * @param permissionMatch1
     * @param uri
     * @param method
     * @param serviceName
     * @return
     */
    public Permission match1(List<Permission> permissionMatch1, String uri, String method, String serviceName) {
        //循环匹配
        for (Permission permission : permissionMatch1) {
            String matchUrl = permission.getUrl();
            String matchMethod = permission.getMethod();
            AntPathMatcher antPathMatcher = new AntPathMatcher();
            // path路径是否符合pattern的规范
            boolean match = antPathMatcher.match(matchUrl, uri);
            if (match) {
                //匹配提交方式
                if (!matchMethod.equals("*") && matchMethod.equalsIgnoreCase(method) && serviceName.equals(permission.getServiceName())) {
                    return permission;
                }
            }
        }
        return null;
    }


    /***
     * 令牌校验
     */
    public Map<String, Object> tokenIntercept(ServerWebExchange exchange) {
        //request
        ServerHttpRequest request = exchange.getRequest();
        //客户端IP
        String ip = IpUtil.getIp(request);
        //用户令牌
        String token = request.getHeaders().getFirst("authorization");
        //令牌校验
        Map<String, Object> resultMap = AuthorizationIntterceptor.jwtVerify(token, ip);
        return resultMap;
    }

    /*****
     * 令牌解析
     */
    public static Map<String, Object> jwtVerify(String token, String clientIp) {
        try {
            //解析Token
            Map<String, Object> dataMap = JwtToken.parseToken(token);
            //获取Token中IP的MD5
            String ip = dataMap.get("ip").toString();
            //比较Token中IP的MD5值和用户的IPMD5值
            if (ip.equals(MD5.md5(clientIp))) {
                return dataMap;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
