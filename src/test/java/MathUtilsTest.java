package edu.cyb535;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MathUtilsTest {

    private MathUtils mathUtils;

    @BeforeEach
    void setUp() {
        mathUtils = new MathUtils();
    }

    @Test
    void testAdd() {
        assertEquals(5, mathUtils.add(2, 3));
        assertEquals(-1, mathUtils.add(2, -3));
    }

    @Test
    void testSubtract() {
        assertEquals(2, mathUtils.subtract(5, 3));
        assertEquals(-2, mathUtils.subtract(3, 5));
    }

    @Test
    void testMultiply() {
        assertEquals(12, mathUtils.multiply(4, 3));
        assertEquals(0, mathUtils.multiply(0, 5));
    }

    @Test
    void testDivide() {
        assertEquals(5.0, mathUtils.divide(10, 2));
        assertEquals(-1.0, mathUtils.divide(5, 0));
    }
}
