package com.cognizant.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class ExceptionThrowerTest {

    @Test
    public void testThrowException() {

        ExceptionThrower exceptionThrower = new ExceptionThrower();

        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> exceptionThrower.throwException()
        );

        assertEquals("Invalid argument", exception.getMessage());
    }
}
