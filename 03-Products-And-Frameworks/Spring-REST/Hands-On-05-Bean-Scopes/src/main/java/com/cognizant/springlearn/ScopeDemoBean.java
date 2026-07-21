package com.cognizant.springlearn;

import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ScopeDemoBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScopeDemoBean.class);
    private static final AtomicInteger CONSTRUCTION_COUNT = new AtomicInteger(0);

    private final int instanceId;

    public ScopeDemoBean() {
        instanceId = CONSTRUCTION_COUNT.incrementAndGet();
        LOGGER.info("ScopeDemoBean constructed, instanceId={}, totalConstructs={}", instanceId, CONSTRUCTION_COUNT.get());
    }

    public int getInstanceId() {
        return instanceId;
    }

    public static int getConstructionCount() {
        return CONSTRUCTION_COUNT.get();
    }
}
