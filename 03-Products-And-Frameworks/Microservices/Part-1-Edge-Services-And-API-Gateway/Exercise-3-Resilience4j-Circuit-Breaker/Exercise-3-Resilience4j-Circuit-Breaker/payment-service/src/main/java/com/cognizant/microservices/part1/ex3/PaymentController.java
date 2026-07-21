package com.cognizant.microservices.part1.ex3;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class PaymentController {
    @Value("${payment.fail:false}")
    private boolean fail;

    @GetMapping("/payments/charge")
    public Map<String, Object> charge(@RequestParam(defaultValue = "100") int amount) {
        if (fail) {
            throw new IllegalStateException("Payment provider unavailable");
        }
        return Map.of("status", "SUCCESS", "amount", amount);
    }
}
