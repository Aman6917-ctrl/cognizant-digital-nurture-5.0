package com.cognizant.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SubtractionTest {

    @Test
    public void testSubtraction() {

        Calculator calculator = new Calculator();

        assertEquals(2, calculator.subtract(5, 3));
    }
}
