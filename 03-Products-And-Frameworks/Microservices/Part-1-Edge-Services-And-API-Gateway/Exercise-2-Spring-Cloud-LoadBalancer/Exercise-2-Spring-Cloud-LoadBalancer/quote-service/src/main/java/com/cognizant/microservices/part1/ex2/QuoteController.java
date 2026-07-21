package com.cognizant.microservices.part1.ex2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class QuoteController {
    @Value("${quote.instance-id:default}")
    private String instanceId;

    @GetMapping("/quotes/random")
    public Map<String, String> random() {
        return Map.of("instance", instanceId, "quote", "Learning microservices with Spring Cloud");
    }
}
