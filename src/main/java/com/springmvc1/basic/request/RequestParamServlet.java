package com.springmvc1.basic.request;

import java.io.IOException;
import java.util.Arrays;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException {

        request.getParameterNames().asIterator().forEachRemaining(
                paramName -> System.out.println(paramName + " -> " + request.getParameter(paramName)));

        String username = request.getParameter("username");
        System.out.println(username);
        String age = request.getParameter("age");
        System.out.println(age);

        String[] usernames = request.getParameterValues("username");

        Arrays.stream(usernames).forEach(System.out::println);

        response.getWriter().write("ok");
    }
}
