package com.example.interceptor;

import com.example.util.JwtToken;
import com.example.util.MD5;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class AuthorizationInterceptor {
    /**
     * logger
     */
    private static final Logger logger = LoggerFactory.getLogger(AuthorizationInterceptor.class);

    /**
     * 令牌解析
     */
    public static Map<String, Object> jwtVerify(String token, String clientIp) throws Exception {
        //token 解析
        Map<String, Object> resultMap = JwtToken.parseToken(token);

        //获取令牌中的ip
        String ip = resultMap.get("ip").toString();

        //ip校验
        String md5Ip = MD5.md5(clientIp);
        logger.info("当前ip为{},加密过后的ip为{},token解析ip为{}", clientIp, md5Ip, ip);
        if (StringUtils.equals(md5Ip, ip)) {
            return resultMap;
        }
        return null;
    }
}
