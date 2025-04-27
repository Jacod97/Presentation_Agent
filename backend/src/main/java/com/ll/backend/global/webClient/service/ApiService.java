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

    public Mono<String> postGenerateScript() {
        return webClientBuilder.build()
<<<<<<< HEAD
                .get()
=======
                .post()
>>>>>>> c6a8cff564708b79e95dfa7a4d6ec73a059a931a
                .uri("http://127.0.0.1:8000/generate-script")
                .retrieve()
                .bodyToMono(String.class);
    }
}
