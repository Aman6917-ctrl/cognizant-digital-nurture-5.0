package com.cognizant.microservices.part3;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class InventoryController {
    @GetMapping("/inventory/{sku}")
    public Map<String, String> get(@PathVariable("sku") String id) {
        return Map.of("service", "Inventory Service", "id", id);
    }
}
