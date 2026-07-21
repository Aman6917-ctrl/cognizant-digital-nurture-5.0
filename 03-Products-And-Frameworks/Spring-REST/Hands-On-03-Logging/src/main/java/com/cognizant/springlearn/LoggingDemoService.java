package com.cognizant.springlearn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LoggingDemoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingDemoService.class);

    public void runBasicFlow() {
        LOGGER.info("START runBasicFlow");
        LOGGER.debug("Processing userId={}", 101);
        LOGGER.debug("Step completed: validation");
        LOGGER.info("END runBasicFlow");
    }

    public void runWarningScenario() {
        LOGGER.info("START runWarningScenario");
        LOGGER.debug("Cache miss for key={}", "config-v1");
        LOGGER.warn("Deprecated API invoked; migrate to v2");
        LOGGER.info("END runWarningScenario");
    }

    public void runErrorScenario() {
        LOGGER.info("START runErrorScenario");
        LOGGER.debug("Attempting remote call to endpoint={}", "/legacy/sync");
        try {
            simulateFailure();
        } catch (IllegalStateException ex) {
            LOGGER.error("Remote call failed", ex);
        }
        LOGGER.info("END runErrorScenario");
    }

    public void runAllDemos() {
        LOGGER.info("START runAllDemos");
        runBasicFlow();
        runWarningScenario();
        runErrorScenario();
        LOGGER.info("END runAllDemos");
    }

    private void simulateFailure() {
        throw new IllegalStateException("Simulated downstream failure");
    }
}
