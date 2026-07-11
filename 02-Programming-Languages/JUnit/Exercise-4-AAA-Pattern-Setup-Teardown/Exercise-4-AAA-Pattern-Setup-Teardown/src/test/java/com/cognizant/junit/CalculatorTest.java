package com.cognizant.junit;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CalculatorTest {

    private Calculator calculator;

    @Before
    public void setUp() {
        calculator = new Calculator();
        System.out.println("Setup: Calculator initialized");
    }

    @After
    public void tearDown() {
        calculator = null;
        System.out.println("Teardown: Calculator object cleared");
    }

    @Test
    public void testAddition() {

        // Arrange
        int a = 5;
        int b = 3;
        int expected = 8;

        // Act
        int actual = calculator.add(a, b);

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    public void testSubtraction() {

        // Arrange
        int a = 10;
        int b = 4;
        int expected = 6;

        // Act
        int actual = calculator.subtract(a, b);

        // Assert
        assertEquals(expected, actual);
    }
}
