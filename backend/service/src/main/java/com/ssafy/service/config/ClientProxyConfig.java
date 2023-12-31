package com.ssafy.service.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Client;
import feign.Feign;
import feign.codec.Encoder;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@RequiredArgsConstructor
public class ClientProxyConfig {
    @Bean
    public Feign.Builder feignBuilder(Client feignClientProxy , Encoder encoder) {
        return Feign.builder()
                .encoder(encoder)
                .client(feignClientProxy);
    }

    @Bean
    @Primary
    public Encoder feignEncoder() {
        return new ByteArrayEncoder(new ObjectMapper());
    }
}
