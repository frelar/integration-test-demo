package com.example;

import org.junit.*;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.servlet.Context;
import org.mortbay.jetty.servlet.ServletHolder;

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
    private static Server server;

    @BeforeClass
    public static void setUpServer() throws Exception {
        server = new Server(8080);
        Context root = new Context(server, "/integration-test-demo", Context.SESSIONS);
        root.addServlet(new ServletHolder(new SquareServlet()), "/app/*");
        server.start();
    }

    @Before
    public void setUpClient() throws Exception {
        URL url = new URL("http://localhost:8080/integration-test-demo/app?base=10");
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");
    }

    @After
    public void tearDownClient() throws Exception {
        connection.disconnect();
    }

    @AfterClass
    public static void tearDownServer() throws Exception {
        server.stop();
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
