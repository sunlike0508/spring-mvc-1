package com.springmvc1.web.frontcontroller.v1;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import com.springmvc1.web.frontcontroller.v1.controller.MemberFormControllerV1;
import com.springmvc1.web.frontcontroller.v1.controller.MemberListControllerV1;
import com.springmvc1.web.frontcontroller.v1.controller.MemberSaveControllerV1;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "frontControllerServletV1", urlPatterns = "/front-controller/v1/*")
public class FrontControllerServletV1 extends HttpServlet {

    private final Map<String, ControllerV1> controllerV1Map = new HashMap<>();


    public FrontControllerServletV1() {
        controllerV1Map.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
        controllerV1Map.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
        controllerV1Map.put("/front-controller/v1/members", new MemberListControllerV1());
    }


    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("FrontControllerServletV1.service");

        String requestURI = request.getRequestURI();

        ControllerV1 controllerV1 = controllerV1Map.get(requestURI);

        if(controllerV1 == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        controllerV1.process(request, response);
    }
}
