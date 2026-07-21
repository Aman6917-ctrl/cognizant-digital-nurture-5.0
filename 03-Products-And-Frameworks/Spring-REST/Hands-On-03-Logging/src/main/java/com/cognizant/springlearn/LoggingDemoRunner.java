package com.cognizant.springlearn;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("!test")
public class LoggingDemoRunner implements ApplicationRunner {

    private final LoggingDemoService loggingDemoService;

    public LoggingDemoRunner(LoggingDemoService loggingDemoService) {
        this.loggingDemoService = loggingDemoService;
    }

    @Override
    public void run(org.springframework.boot.ApplicationArguments args) {
        loggingDemoService.runAllDemos();
    }
}
