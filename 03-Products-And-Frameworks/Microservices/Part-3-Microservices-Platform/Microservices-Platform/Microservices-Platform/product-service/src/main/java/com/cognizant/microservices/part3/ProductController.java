package com.cognizant.microservices.part3;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ProductController {
    @GetMapping("/products/{id}")
    @Cacheable("products")
    public Map<String, String> get(@PathVariable("id") String id) {
        return Map.of("service", "Product Service", "id", id);
    }
}
