package com.zhao.springcloud.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhao.springcloud.provider.ResponseProvider;
import com.zz.jwt.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

@Slf4j
@Component
@AllArgsConstructor
public class AuthFilter implements GlobalFilter, Ordered {
    private final ObjectMapper objectMapper;
    private final AntPathMatcher antPathMatcher = new AntPathMatcher();

    public static final String AUTH_KEY = "token";

    /**
     * 密钥
     */
    public static final String DEFAULT_SECRET_KEY = "zzxisapowerfulmicroservicearchitectureupgradedandoptimizedfromacommercialproject";
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpResponse resp = exchange.getResponse();
        //校验 Token 放行
        String path = exchange.getRequest().getURI().getPath();
        if (isSkip(path)) {
            return chain.filter(exchange);
        }
        String headerToken = exchange.getRequest().getHeaders().getFirst(AUTH_KEY);
        String paramToken = exchange.getRequest().getQueryParams().getFirst(AUTH_KEY);
        if (StringUtils.isEmpty(headerToken) && StringUtils.isEmpty(paramToken)) {
            return unAuth(resp, "缺失令牌,鉴权失败");
        }
        String token = StringUtils.isEmpty(headerToken) ? paramToken : headerToken;
        Claims claims = JwtUtil.parseJWT(token, DEFAULT_SECRET_KEY);
        if (token == null || claims == null) {
            return unAuth(resp, "请求未授权");
        }
        if (JwtUtil.isTokenExpired(claims)) {
            return unAuth(resp, "请重新登录");
        }
        return chain.filter(exchange);
    }

    private boolean isSkip(String path) {
        // 白名单匹配，跳过认证
        return false;
    }

    private Mono<Void> unAuth(ServerHttpResponse resp, String msg) {
        // 大环境返回200正常状态码  内部401 做校验失效处理
        resp.setStatusCode(HttpStatus.OK);
        resp.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        String result = "";
        try {
            result = objectMapper.writeValueAsString(ResponseProvider.unAuth(msg));
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
        }
        DataBuffer buffer = resp.bufferFactory().wrap(result.getBytes(StandardCharsets.UTF_8));
        return resp.writeWith(Flux.just(buffer));
    }


    @Override
    public int getOrder() {
        return -100;
    }

}
