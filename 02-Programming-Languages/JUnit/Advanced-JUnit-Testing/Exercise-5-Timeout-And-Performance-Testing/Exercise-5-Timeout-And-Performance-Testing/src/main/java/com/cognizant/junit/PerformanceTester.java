package com.cognizant.junit;

public class PerformanceTester {

    public void performTask() {

        for (int i = 0; i < 1000; i++) {
            Math.sqrt(i);
        }
    }
}
