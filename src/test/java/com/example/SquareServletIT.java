package com.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

/**
 * @author Fredrik Larsson (frelar@gmail.com)
 * @since 1.0
 */
public class SquareServletIT {

    private HttpURLConnection connection;

    @Before
    public void setUp() throws Exception {
        URL url = new URL("http://localhost:8080/integration-test-demo/app?base=10");
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");
    }

    @After
    public void tearDown() throws Exception {
        connection.disconnect();
    }

    @Test
    public void requestWithBaseParameterShouldReturnJson() throws Exception {
        assertEquals(SquareServlet.CONTENT_TYPE, connection.getHeaderField("Content-Type"));
    }

    @Test
    public void requestWithBaseParameterShouldBeValid() throws Exception {
        assertEquals(200, connection.getResponseCode());
    }

    @Test
    public void testSquare() throws Exception {
        assertEquals("{\"value\":100}", getResponseBody());
    }

    public String getResponseBody() throws Exception {
        Scanner s = new Scanner(connection.getInputStream()).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
}
