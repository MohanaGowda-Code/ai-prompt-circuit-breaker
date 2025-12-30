package com.example.ai_prompt_circuit_breaker.controller;


import com.example.ai_prompt_circuit_breaker.dto.AiRequest;
import com.example.ai_prompt_circuit_breaker.dto.AiResponse;
import com.example.ai_prompt_circuit_breaker.service.AiPromptService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/ai")
public class AiController {

    private final AiPromptService service;

    public AiController(AiPromptService service) {
        this.service = service;
    }

    @PostMapping("/prompt/circuit")
    public Mono<AiResponse> prompt(@RequestBody AiRequest request) {
        return service.sendPrompt(request.prompt())
                .map(AiResponse::new);
    }
}
