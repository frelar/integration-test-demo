package com.example;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Fredrik Larsson (frelar@gmail.com)
 * @since 1.0
 */
public class SquareServlet extends HttpServlet {

    public static final String CONTENT_TYPE = "application/json; charset=UTF-8";
    public static final String PARAMETER_NAME = "base";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final int base = Integer.parseInt(request.getParameter(PARAMETER_NAME));
        response.setContentType(CONTENT_TYPE);
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write("{\"value\":" + square(base) + "}");
    }

    static int square(int base) {
        return base*base;
    }
}
