package com.cognizant.ems.hibernate.config;

import com.cognizant.ems.hibernate.service.BatchInsertDemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@RequiredArgsConstructor
public class BatchDemoRunner {

    private final BatchInsertDemoService batchInsertDemoService;

    @Bean
    @Profile("!test")
    CommandLineRunner runBatchDemo() {
        return args -> batchInsertDemoService.insertManyEmployees(50);
    }
}
