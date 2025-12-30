package com.example.ai_prompt_circuit_breaker.service;


import com.example.ai_prompt_circuit_breaker.dto.AiResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.Map;

@Service
public class AiPromptService {

    private final WebClient webClient;

    public AiPromptService(WebClient webClient) {
        this.webClient = webClient;
    }

    @Value("${openai.api.url}")
    private String apiUrl;

    @Value("${openai.api.key}")
    private String apiKey;


    @CircuitBreaker(name = "openaiService", fallbackMethod = "fallback")
    public Mono<String> sendPrompt(String prompt){

        Map<String, Object> requestBody = Map.of(
                "model", "gpt-3.5-turbo",
                "messages", new Object[]{
                        Map.of("role", "user", "content", prompt)
                }
        );

        return webClient.post()
                .uri(apiUrl)
                .header("Authorization", "Bearer " + apiKey)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .timeout(Duration.ofSeconds(10))
                .retryWhen(Retry.backoff(2, Duration.ofSeconds(2)));

    }

    private Mono<String> fallback(String prompt, Throwable ex) {
        return Mono.just("AI service unavailable. Fallback response.");
    }

}
