package com.example.config;

import com.alibaba.csp.sentinel.adapter.gateway.common.SentinelGatewayConstants;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiDefinition;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiPathPredicateItem;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiPredicateItem;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.GatewayApiDefinitionManager;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayFlowRule;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayRuleManager;
import com.alibaba.csp.sentinel.adapter.gateway.sc.SentinelGatewayFilter;
import com.alibaba.csp.sentinel.adapter.gateway.sc.exception.SentinelGatewayBlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.result.view.ViewResolver;

import javax.annotation.PostConstruct;
import java.util.Collections;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration
public class GatewayConfiguration {
    private final List<ViewResolver> viewResolvers;
    private final ServerCodecConfigurer serverCodecConfigurer;

    public GatewayConfiguration(ObjectProvider<List<ViewResolver>> listObjectProvider,
                                ServerCodecConfigurer serverCodecConfigurer) {
        this.serverCodecConfigurer = serverCodecConfigurer;
        this.viewResolvers = listObjectProvider.getIfAvailable(Collections::emptyList);
    }

    /**
     * 限流异常处理
     */
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SentinelGatewayBlockExceptionHandler sentinelGatewayBlockExceptionHandler() {
        return new SentinelGatewayBlockExceptionHandler(viewResolvers, serverCodecConfigurer);
    }

    /**
     * Sentinel路由处理核心过滤器
     */
    @Bean
    @Order(-1)
    public GlobalFilter sentinelGatewayFileter() {
        return new SentinelGatewayFilter();
    }

    /**
     * Api定义
     */
    private void initCustomizedApis() {
        //Api集合
        Set<ApiDefinition> definitions = new HashSet<ApiDefinition>();

        ApiDefinition cartApi = new ApiDefinition("mall_cart_api")
                .setPredicateItems(new HashSet<ApiPredicateItem>() {{
                    // /cart/list
                    add(new ApiPathPredicateItem().setPattern("/cart/list"));
                    // /cart/*/*
                    add(new ApiPathPredicateItem().setPattern("/cart/**")
                            //根据前缀匹配
                            .setMatchStrategy(SentinelGatewayConstants.URL_MATCH_STRATEGY_PREFIX));
                }});
        definitions.add(cartApi);
        //加载Api
        GatewayApiDefinitionManager.loadApiDefinitions(definitions);
    }

    /**
     * 限流规则定义
     */
    public void initGatewayRules() {
        //创建集合存储所有规则
        Set<GatewayFlowRule> rules = new HashSet<>();
        rules.add(new GatewayFlowRule("goods_route")
                .setCount(2).setIntervalSec(2).setBurst(2)
                .setMaxQueueingTimeoutMs(1000).setControlBehavior(RuleConstant.CONTROL_BEHAVIOR_RATE_LIMITER));

        rules.add(new GatewayFlowRule("mall_cart_api")
                .setCount(2).setIntervalSec(1));

        GatewayRuleManager.loadRules(rules);
    }

    /**
     * 初始化加载Api和规则
     */
    @PostConstruct
    public void init(){
        initCustomizedApis();
        initGatewayRules();
    }
}
