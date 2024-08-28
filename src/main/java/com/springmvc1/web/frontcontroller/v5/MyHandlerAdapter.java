package com.springmvc1.web.frontcontroller.v5;

import java.io.IOException;
import com.springmvc1.web.frontcontroller.ModelView;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface MyHandlerAdapter {

    boolean supports(Object handler);


    ModelView handle(HttpServletRequest request, HttpServletResponse response, Object object)
            throws ServletException, IOException;
}
