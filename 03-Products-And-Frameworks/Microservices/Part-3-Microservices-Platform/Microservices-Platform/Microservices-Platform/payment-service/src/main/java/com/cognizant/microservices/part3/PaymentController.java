package com.cognizant.microservices.part3;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class PaymentController {
    private final BillingClient billingClient;
    public PaymentController(BillingClient billingClient) { this.billingClient = billingClient; }

    @GetMapping("/payments/{id}")
    public Map<String, Object> get(@PathVariable("id") String id) {
        return Map.of("service", "Payment Service", "id", id, "billing", billingClient.getBilling(id));
    }
}
