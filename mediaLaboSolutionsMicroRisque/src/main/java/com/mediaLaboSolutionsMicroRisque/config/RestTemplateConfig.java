package com.mediaLaboSolutionsMicroRisque.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Configuration Spring permettant d'exposer un bean RestTemplate
 */
@Configuration
public class RestTemplateConfig {

    /**
     * Déclare une bean RestTemplate utilisable dans toute l'application
     * @return RestTemplate
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
