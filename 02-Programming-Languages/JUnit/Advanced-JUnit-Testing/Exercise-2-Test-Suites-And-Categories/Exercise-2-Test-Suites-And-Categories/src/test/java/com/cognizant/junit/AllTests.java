package com.cognizant.junit;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
    AdditionTest.class,
    SubtractionTest.class
})
public class AllTests {
}
