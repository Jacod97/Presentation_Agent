package com.ll.backend.domain.home.controller;

import com.ll.backend.global.webClient.service.ApiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class HomeController {

    private final ApiService apiService;

    public HomeController(ApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping("/")
    public Mono<String> home() {
        return apiService.postGenerateScript();
    }
}
