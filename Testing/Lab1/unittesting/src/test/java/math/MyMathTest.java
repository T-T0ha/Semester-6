package math;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MyMathTest {

    MyMath myMath = new MyMath();

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void factorial() {
        int expected = 720;
        int actual = myMath.factorial(6);
        assertEquals(expected, actual);
    }

    @Test
    public void inputZeroFactorial() {
        int expected = 1;
        int actual = myMath.factorial(0);
        assertEquals(expected,actual);
    }

    @Test (expected = IllegalArgumentException.class)
    public void numberIsNegative() {
        myMath.factorial(-2);
    }

    @Test (expected = IllegalArgumentException.class)
    public void numberIsGreaterThan12() {
        myMath.factorial(13);
    }

    @Test
    public void isPrime() {
        assertTrue(myMath.isPrime(17));
    }

    @Test
    public void isNotPrime() {
        assertFalse( myMath.isPrime(12));
    }

    @Test (expected = IllegalArgumentException.class)
    public void inputIsLessThanTwo(){
        myMath.isPrime(1);
    }
}