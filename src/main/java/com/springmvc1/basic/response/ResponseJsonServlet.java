package com.springmvc1.basic.response;

import java.io.IOException;
import java.io.PrintWriter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springmvc1.basic.HelloData;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet(name = "responseJsonServlet", urlPatterns = "/response-json")
public class ResponseJsonServlet extends HttpServlet {

    private final ObjectMapper objectMapper = new ObjectMapper();


    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        HelloData helloData = new HelloData();
        helloData.setUsername("shin");
        helloData.setAge(10);

        PrintWriter writer = response.getWriter();
        writer.println(objectMapper.writeValueAsString(helloData));

    }

}
