package com.cognizant.ems.hibernate.web;

import com.cognizant.ems.hibernate.service.BatchInsertDemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/batch")
@RequiredArgsConstructor
public class BatchStatusController {

    private final BatchInsertDemoService batchInsertDemoService;

    @GetMapping("/demo-status")
    public Map<String, Object> status() {
        return Map.of(
                "lastBatchInsertCount", batchInsertDemoService.getLastBatchCount(),
                "totalEmployees", batchInsertDemoService.totalEmployees());
    }
}
