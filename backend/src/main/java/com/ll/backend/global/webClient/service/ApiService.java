package com.ll.backend.global.webClient.service;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Configuration
public class ApiService {

    private final WebClient.Builder webClientBuilder;

    public ApiService(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    public Mono<String> getExternalData() {
        return webClientBuilder.build()
                .get()
                .uri("http://127.0.0.1:8000/generate-script")
                .retrieve()
                .bodyToMono(String.class);
    }
}
