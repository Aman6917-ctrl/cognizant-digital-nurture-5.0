package com.cognizant.microservices.part1.ex3;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
public class FallbackController {
    @GetMapping("/fallback/payments")
    public Mono<Map<String, String>> paymentFallback() {
        return Mono.just(Map.of("status", "FALLBACK", "message", "Payment service temporarily unavailable"));
    }
}
