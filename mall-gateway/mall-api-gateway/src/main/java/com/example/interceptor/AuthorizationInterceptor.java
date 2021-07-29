package com.example.interceptor;

import com.example.util.JwtToken;
import com.example.util.MD5;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public class AuthorizationInterceptor {
    /**
     * 令牌解析
     */
    public static Map<String, Object> jwtVerify(String token, String clientIp) throws Exception {
        //token 解析
        Map<String, Object> resultMap = JwtToken.parseToken(token);

        //获取令牌中的ip
        String ip = resultMap.get("ip").toString();
        //ip校验
        clientIp = MD5.md5(ip);
        if (StringUtils.equals(clientIp, ip)) {
            return resultMap;
        }
        return null;
    }
}
