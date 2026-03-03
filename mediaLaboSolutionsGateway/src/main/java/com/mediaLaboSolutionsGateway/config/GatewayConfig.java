package com.mediaLaboSolutionsGateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** Configuration des routes du Spring Cloud Gateway.
 * Cette classe définit l'ensemble des règles de routage permettant de rediriger les requêtes entrantes vers les microservices appropriés.
 */
@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                // Redirection des requêtes liées aux patients vers le patient-service
                .route("patient-service", r -> r
                        .path("/api/patients/**")
                        .uri("http://patient-service:8081"))
                // Redirection des requêtes liées aux adresses vers le patient-service
                .route("adresse-service", r -> r
                        .path("/api/adresses/**")
                        .uri("http://patient-service:8081"))
                // Redirection des requêtes liées aux genres vers le patient-service
                .route("genre-service", r -> r
                        .path("/api/genres/**")
                        .uri("http://patient-service:8081"))
                // Redirection des requêtes liées aux téléphones vers le patient-service
                .route("telephone-service", r -> r
                        .path("/api/telephones/**")
                        .uri("http://patient-service:8081"))
                // Redirection des requêtes liées aux notes vers le notes-service
                .route("notes-service", r -> r
                        .path("/api/notes/**")
                        .uri("http://notes-service:8082"))
                // Redirection des requêtes liées à l'évaluation du risque vers le risk-service
                .route("assessment-service", r -> r
                        .path("/api/assess/**")
                        .uri("http://risk-service:8083"))
                .build();
    }
}
