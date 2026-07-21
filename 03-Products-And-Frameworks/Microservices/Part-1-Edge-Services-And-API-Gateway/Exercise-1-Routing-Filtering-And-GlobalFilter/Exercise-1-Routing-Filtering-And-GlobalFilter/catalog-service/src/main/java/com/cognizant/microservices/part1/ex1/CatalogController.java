package com.cognizant.microservices.part1.ex1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class CatalogController {
    @GetMapping("/catalog/items")
    public Map<String, Object> items() {
        return Map.of("service", "catalog-service", "items", new String[] {"book", "pen"});
    }
}
