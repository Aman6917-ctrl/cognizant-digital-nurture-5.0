package com.cognizant.microservices.part3;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;

@Configuration
@EnableCaching
public class ProductCacheConfig {
    @Bean
    ConcurrentMapCacheManager cacheManager() {
        return new ConcurrentMapCacheManager("products");
    }
}
