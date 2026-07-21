package com.cognizant.microservices.part3;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
public class GatewayFallbackController {
    @GetMapping("/fallback/orders")
    public Mono<Map<String, String>> orderFallback() {
        return Mono.just(Map.of("status", "FALLBACK", "service", "order-service"));
    }
}
