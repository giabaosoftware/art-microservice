package com.baobao.api_gateway.config;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class GatewayConfig {
    final AuthenticationFilter filter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder){
        return builder.routes()
                .route("artwork-service",r-> r.path("/api/v1/artworks/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://ARTWORK-SERVICE"))
                .route("account-service",r-> r.path("/api/v1/accounts/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://ACCOUNT-SERVICE"))
                .build();
    }
}
