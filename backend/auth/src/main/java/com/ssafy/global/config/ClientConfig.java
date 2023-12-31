package com.ssafy.global.config;

import feign.Client;
import feign.Feign;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientConfig {
    @Bean
    public Feign.Builder feignBuilder(Client feignClient) {
        return Feign.builder()
                .client(feignClient);
    }
}

