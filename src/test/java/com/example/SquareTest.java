package com.example;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Fredrik Larsson (frelar@gmail.com)
 * @since 1.0
 */
public class SquareTest {
    @Test
    public void testSquare() {
        assertEquals("10^2=100", 100, SquareServlet.square(10));
    }
}
