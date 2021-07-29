package com.example.filter;

import com.alibaba.fastjson.JSON;
import com.example.hot.HotQueue;
import com.example.interceptor.AuthorizationInterceptor;
import com.example.util.IpUtil;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: chenq
 * @Description:
 * @Date: Created in 2021-07-21 16:51
 */
@Configuration
public class ApiFilter implements GlobalFilter, Ordered {
    @Autowired
    private HotQueue hotQueue;

    @SneakyThrows
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        //获取uri
        String uri = request.getURI().getPath();

        if (StringUtils.equals(uri,"/mall/user/info/login")) {
            //放行
            return chain.filter(exchange);
        }

        //客户端IP
        String ip = IpUtil.getIp(request);
        //用户令牌
        String token = request.getHeaders().getFirst("authorization");
        //令牌校验
        Map<String, Object> resultMap = AuthorizationInterceptor.jwtVerify(token, ip);
        if (resultMap == null) {
            endProcess(exchange, 401, "no token");
        }


        if (resultMap == null) {
            endProcess(exchange, 401, "no token");
        }

        if (uri.equals("/seckill/order")) {
            //秒杀过滤
            seckillFilter(exchange, request, resultMap.get("username").toString());
        }

        //NOT_HOT 直接由后端服务处理
        return chain.filter(exchange);
    }

    /***
     * 秒杀过滤
     * @param exchange
     * @param request
     * @param username
     */
    private void seckillFilter(ServerWebExchange exchange, ServerHttpRequest request, String username) {
        //商品ID
        String id = request.getQueryParams().getFirst("id");
        //数量
        Integer num = Integer.valueOf(request.getQueryParams().getFirst("num"));

        //排队结果
        int result = hotQueue.hot2Queue(username, id, num);

        //QUEUE_ING、HAS_QUEUE
        if (result == HotQueue.QUEUE_ING || result == HotQueue.HAS_QUEUQ) {
            endProcess(exchange, result, "hot");
        }
    }

    /***
     * 结束程序
     * @param exchange
     * @param code
     * @param message
     */
    public void endProcess(ServerWebExchange exchange, Integer code, String message) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("code", code);
        resultMap.put("message", message);
        exchange.getResponse().setStatusCode(HttpStatus.OK);
        exchange.getResponse().setComplete();
        exchange.getResponse().getHeaders().add("message", JSON.toJSONString(resultMap));
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
