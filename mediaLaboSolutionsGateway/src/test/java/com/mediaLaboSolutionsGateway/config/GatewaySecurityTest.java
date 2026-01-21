package com.mediaLaboSolutionsGateway.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient @Import({GatewayConfig.class, SpringSecurityConfig.class})
public class GatewaySecurityTest {

    @Autowired private WebTestClient webTestClient;

    @Test public void return401NoAuth() {
        webTestClient.get() .uri("/assess/1")
                .exchange()
                .expectStatus().isUnauthorized();
    }

    @Test public void return401WrongAuth() {
        webTestClient.get() .uri("/assess/1")
                .headers(headers -> headers.setBasicAuth("user", "wrongPassword"))
                .exchange() .expectStatus().isUnauthorized(); }
}
