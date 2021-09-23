package com.rvbenlg.apigateway.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Set;

@Component
public class PreFilter implements GlobalFilter, Ordered {

    private final Logger logger = LoggerFactory.getLogger(PreFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        logger.info("Pre filter executed");
        String path = exchange.getRequest().getPath().toString();
        logger.info("Path = " + path);
        HttpHeaders headers = exchange.getRequest().getHeaders();
        Set<String> headerNames = headers.keySet();
        headerNames.forEach(headerName -> logger.info(headerName + " = " + headers.getFirst(headerName)));
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
