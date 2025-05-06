package math;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArithmeticOperationsTest {

    private final ArithmeticOperations arithmeticOperations = new ArithmeticOperations();

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void divide() {
        double expected = 3.0;
        double actual = arithmeticOperations.divide(18, 6);
        assertEquals(expected, actual, 1e-3);
    }

    @Test (expected = ArithmeticException.class)
    public void testZeroInDenominator(){
        arithmeticOperations.divide(35, 0);
    }

    @Test
    public void testDivideNegativeNumerator() {
        double actual = arithmeticOperations.divide(-10, 2);
        assertEquals(-5.0, actual, 1e-3);
    }
    @Test
    public void testDivideNegativeDenominator() {
        double actual = arithmeticOperations.divide(10, -2);
        assertEquals(-5.0, actual, 1e-3);
    }
    @Test
    public void testDivideZeroNumerator() {
        double actual = arithmeticOperations.divide(0, -2);
        assertEquals(0, actual, 1e-3);
    }

    @Test
    public void multiply() {
        double expected = 30;
        double actual = arithmeticOperations.multiply(5, 6);
        assertEquals(expected, actual, 1e-3);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testFirstNumberIsNegative(){
        arithmeticOperations.multiply(-5, 6);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testSecondNumberIsNegative(){
        arithmeticOperations.multiply(5, -6);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testBothNumbersAreNegative(){
        arithmeticOperations.multiply(-5, -6);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testFirstNumberBigProductOutOfBounds(){
        arithmeticOperations.multiply(Integer.MAX_VALUE, 2);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testSecondNumberBigProductOutOfBound(){
        arithmeticOperations.multiply(3, Integer.MAX_VALUE);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testBothNumbersBigProductOutOfBounds(){
        arithmeticOperations.multiply(Integer.MAX_VALUE, Integer.MAX_VALUE);
    }
}