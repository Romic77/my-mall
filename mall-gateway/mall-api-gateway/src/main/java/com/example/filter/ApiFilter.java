package com.example.filter;

import com.alibaba.fastjson.JSON;
import com.example.hot.HotQueue;
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

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        //用户名
        String username = "aa";
        String id = request.getQueryParams().getFirst("id");
        Integer num = Integer.valueOf(request.getQueryParams().getFirst("num"));
        int result = hotQueue.hot2Queue(username, id, num);
        if (result == HotQueue.QUEUE_ING || result == HotQueue.HAS_QUEUQ) {
            //响应状态码200
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("type", "hot");
            resultMap.put("code", result);

            exchange.getResponse().setStatusCode(HttpStatus.OK);
            exchange.getResponse().setComplete();
            exchange.getResponse().getHeaders().add("message", JSON.toJSONString(resultMap));

        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
