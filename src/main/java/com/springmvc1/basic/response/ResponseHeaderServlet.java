package com.springmvc1.basic.response;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;


@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setStatus(HttpServletResponse.SC_OK);
        //response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        //response.setHeader(HttpHeaders.CONTENT_TYPE, "text/plain;charset=utf-8");
        response.setHeader(HttpHeaders.CACHE_CONTROL, "no-cache, no-store, must-revalidate");
        response.setHeader(HttpHeaders.PRAGMA, "no-cache");
        response.setHeader("my-header", "hello");

        content(response);
        cookie(response);
        redirect(response);

        PrintWriter writer = response.getWriter();
        writer.println("ok");
    }


    private void redirect(HttpServletResponse response) throws IOException {

        //response.setStatus(HttpServletResponse.SC_FOUND);
        //response.setHeader("Location", "/basic/hello-form.html");
        response.sendRedirect("/basic/hello-form.html");
    }


    private void content(HttpServletResponse response) {

        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
    }


    private void cookie(HttpServletResponse response) {
        Cookie cookie = new Cookie("myCookie", "good");
        cookie.setMaxAge(600);
        response.addCookie(cookie);
    }
}
