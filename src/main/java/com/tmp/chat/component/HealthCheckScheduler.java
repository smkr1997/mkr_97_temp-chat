package com.tmp.chat.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class HealthCheckScheduler {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${health.check.url:http://localhost:8080/actuator/health}")
    private String healthCheckUrl;

    @Scheduled(fixedRate = 840000) // 14 minutes (600,000 ms)
    public void sendHealthCheck() {
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(healthCheckUrl, String.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                log.info("Health check successful: " + response.getBody());
            } else {
                log.info("Health check failed with status code: " + response.getStatusCode());
            }
        } catch (Exception e) {
            log.error("Error during health check: " + e);
        }
    }
}