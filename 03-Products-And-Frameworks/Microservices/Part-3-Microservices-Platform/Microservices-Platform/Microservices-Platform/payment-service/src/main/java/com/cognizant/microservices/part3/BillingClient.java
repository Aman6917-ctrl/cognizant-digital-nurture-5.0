package com.cognizant.microservices.part3;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@FeignClient(name = "billing-service")
public interface BillingClient {
    @GetMapping("/billing/{id}")
    Map<String, Object> getBilling(@PathVariable("id") String id);
}
