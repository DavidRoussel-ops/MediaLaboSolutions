package com.mediaLaboSolutionsGateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("patient-service", r -> r
                        .path("/api/patients/**")
                        .uri("http://localhost:8081"))
                .route("adresse-service", r -> r
                        .path("/api/adresses/**")
                        .uri("http://localhost:8081"))
                .route("genre-service", r -> r
                        .path("/api/genres/**")
                        .uri("http://localhost:8081"))
                .route("telephone-service", r -> r
                        .path("/api/telephones/**")
                        .uri("http://localhost:8081"))
                .build();
    }
}
