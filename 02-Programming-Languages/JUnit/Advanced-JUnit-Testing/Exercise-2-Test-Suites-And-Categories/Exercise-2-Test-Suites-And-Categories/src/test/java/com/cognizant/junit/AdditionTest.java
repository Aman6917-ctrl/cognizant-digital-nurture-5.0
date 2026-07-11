package com.cognizant.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AdditionTest {

    @Test
    public void testAddition() {

        Calculator calculator = new Calculator();

        assertEquals(5, calculator.add(2, 3));
    }
}
