package com.example.ai_prompt_circuit_breaker.exception;

import com.example.ai_prompt_circuit_breaker.dto.AiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public Mono<ResponseEntity<AiResponse>> handleAll(Exception ex) {
        return Mono.just(
                ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new AiResponse("Unexpected error: " + ex.getMessage()))
        );
    }
}
