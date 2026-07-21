package com.cognizant.microservices.part3;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class OrderController {

    private final InventoryClient inventoryClient;
    public OrderController(InventoryClient inventoryClient) { this.inventoryClient = inventoryClient; }

    @GetMapping("/orders/{id}")
    public Map<String, Object> get(@PathVariable("id") String id) {
        Map<String, Object> inventory = inventoryClient.getInventory("SKU-" + id);
        return Map.of("service", "Order Service", "id", id, "inventory", inventory);
    }
}
