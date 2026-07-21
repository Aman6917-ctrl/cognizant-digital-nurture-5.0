package com.cognizant.ems.hibernate;

import com.cognizant.ems.hibernate.service.BatchInsertDemoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@org.springframework.test.context.ActiveProfiles("test")
class BatchInsertDemoServiceTest {

    @Autowired
    private BatchInsertDemoService batchInsertDemoService;

    @Test
    void contextLoads() {
        assertThat(batchInsertDemoService).isNotNull();
    }

    @Test
    void batchInsertMany() {
        int inserted = batchInsertDemoService.insertManyEmployees(30);
        assertThat(inserted).isEqualTo(30);
        assertThat(batchInsertDemoService.totalEmployees()).isGreaterThanOrEqualTo(30);
    }
}
