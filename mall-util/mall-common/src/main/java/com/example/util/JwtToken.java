package com.example.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class JwtToken {
    //默认密钥
    public static final String DEFAULT_SECRET = "springcloudalibaba";

    /**
     * @param dataMap body数据
     * @param secret  密钥
     * @return 返回jwt
     */
    public static String createToken(Map<String, Object> dataMap, String secret) {
        //密钥
        if (StringUtils.isEmpty(secret)) {
            secret = DEFAULT_SECRET;
        }

        //创建令牌算法
        Algorithm algorithm = Algorithm.HMAC256(secret);

        //创建令牌
        return JWT.create().withClaim("body", dataMap)
                .withIssuer("my-mall")  //签发者
                .withSubject("jwt") //主题
                .withAudience("member") //接收jwt
                .withExpiresAt(new Date(System.currentTimeMillis() + 3600000)) //过期时间
                //.withNotBefore(new Date(System.currentTimeMillis()))  //指定时间之前JWT令牌是不可用的
                .withIssuedAt(new Date()) //签发时间
                .withJWTId(UUID.randomUUID().toString().replace("-", "")) //jwt唯一标识
                .sign(algorithm);

    }

    /**
     * 生成令牌
     */
    public static String createToken(Map<String, Object> dataMap) {
        return createToken(dataMap, null);
    }


    /**
     * @param token  token参数
     * @param secret 密钥
     * @return body
     */
    public static Map<String, Object> parseToken(String token, String secret) {
        //密钥为空就采用默认密钥
        if (StringUtils.isEmpty(secret)) {
            secret = DEFAULT_SECRET;
        }
        Algorithm algorithm = Algorithm.HMAC256(secret);
        JWTVerifier jwtVerifier = JWT.require(algorithm).build();
        DecodedJWT jwt = jwtVerifier.verify(token);
        return jwt.getClaim("body").as(Map.class);
    }

    /**
     * @param token token参数
     * @return
     */
    public static Map<String, Object> parseToken(String token) {
        return parseToken(token, null);
    }

    public static void main(String[] args) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("name", "zhangsan");
        dataMap.put("age", 26);
        dataMap.put("address", "深圳");

        //创建令牌
        String token = createToken(dataMap);
        System.out.println(token);

        //解析令牌
        Map<String, Object> resultMap = parseToken(token);
        System.out.println(resultMap);
    }
}
