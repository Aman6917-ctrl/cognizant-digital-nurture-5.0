package com.cognizant.microservices.part3;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@FeignClient(name = "inventory-service")
public interface InventoryClient {
    @GetMapping("/inventory/{sku}")
    Map<String, Object> getInventory(@PathVariable("sku") String sku);
}
