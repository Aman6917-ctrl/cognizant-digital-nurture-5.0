package com.cognizant.junit;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrderedTests {

    @Test
    @Order(3)
    public void testThird() {
        System.out.println("Executing Third Test");
    }

    @Test
    @Order(1)
    public void testFirst() {
        System.out.println("Executing First Test");
    }

    @Test
    @Order(2)
    public void testSecond() {
        System.out.println("Executing Second Test");
    }
}
