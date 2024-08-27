package com.springmvc1.basic.request;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.StreamUtils;

@WebServlet(name = "requestBodyStringServlet", urlPatterns = "/request-body-string")
public class RequestBodyStringServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();

        StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        System.out.println("message body = " + getServletName());

        response.getWriter().write("ok");
    }
}
