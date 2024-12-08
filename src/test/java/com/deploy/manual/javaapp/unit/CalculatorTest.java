package com.deploy.manual.javaapp.unit;


import org.junit.jupiter.api.Test;

import com.deploy.manual.javaapp.Calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {

    @Test
    public void testAdd() {
        Calculator calculator = new Calculator();
        int result = calculator.add(2, 3);
        assertEquals(5, result);
    }

    // Additional test methods for other Calculator methods
}
