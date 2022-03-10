package com.example.hw5;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Before
    public void setUp() {
        mCalculator= new Calculator();
    }
    private Calculator mCalculator;
    //    public void addition_isCorrect() {
//        assertEquals(5, 2 + 2);
//    }
    @Test
    public void testnumber1() {
        double resultpow = mCalculator.pow(2d, 2d);
        assertThat(resultpow, is(equalTo(4d)));
    }

}