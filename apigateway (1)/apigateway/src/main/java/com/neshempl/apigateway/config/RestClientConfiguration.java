package com.neshempl.apigateway.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestClientConfiguration {

    // First Method: default
    @Bean
    public RestClient.Builder restClientBuilder() {
        return RestClient.builder();
    }

}