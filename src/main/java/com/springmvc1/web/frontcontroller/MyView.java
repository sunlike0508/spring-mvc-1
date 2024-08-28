package com.springmvc1.web.frontcontroller;

import java.io.IOException;
import java.util.Map;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MyView {

    private final String viewPath;


    public MyView(String viewPath) {
        this.viewPath = viewPath;
    }


    public void render(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }


    public void render(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        model.forEach(request::setAttribute);

        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }
}
