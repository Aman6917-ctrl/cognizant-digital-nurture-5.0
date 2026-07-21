package com.cognizant.hql.quiz.demo;

import com.cognizant.hql.quiz.service.AttemptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("!test")
public class QuizDemoRunner implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(QuizDemoRunner.class);

    private final AttemptService attemptService;

    public QuizDemoRunner(AttemptService attemptService) {
        this.attemptService = attemptService;
    }

    @Override
    public void run(String... args) {
        log.info("=== Hands-On 03: Quiz HQL fetch report ===");
        System.out.println(attemptService.buildAttemptReport(1L));
        log.info("=== Hands-On 03 complete ===");
    }
}
