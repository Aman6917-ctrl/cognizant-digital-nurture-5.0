package com.cognizant.junit;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class CalculatorTest {

    @Test
    public void testAdd() {
        Calculator calculator = new Calculator();

        int result = calculator.add(5, 3);

        assertEquals(8, result);
    }

    @Test
    public void testSubtract() {
        Calculator calculator = new Calculator();

        int result = calculator.subtract(10, 4);

        assertEquals(6, result);
    }

    @Test
    public void testMultiply() {
        Calculator calculator = new Calculator();

        int result = calculator.multiply(5, 4);

        assertEquals(20, result);
    }
}
