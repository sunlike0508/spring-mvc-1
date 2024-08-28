package com.springmvc1.web.frontcontroller.v4;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import com.springmvc1.web.frontcontroller.MyView;
import com.springmvc1.web.frontcontroller.v4.controller.MemberFormControllerV4;
import com.springmvc1.web.frontcontroller.v4.controller.MemberListControllerV4;
import com.springmvc1.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "frontControllerServletV4", urlPatterns = "/front-controller/v4/*")
public class FrontControllerServletV4 extends HttpServlet {

    private final Map<String, ControllerV4> controllerV4Map = new HashMap<>();


    public FrontControllerServletV4() {
        controllerV4Map.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
        controllerV4Map.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
        controllerV4Map.put("/front-controller/v4/members", new MemberListControllerV4());
    }


    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("FrontControllerServletV4.service");

        String requestURI = request.getRequestURI();

        ControllerV4 controllerV4 = controllerV4Map.get(requestURI);

        if(controllerV4 == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        Map<String, String> paramMap = createParamMap(request);
        Map<String, Object> model = new HashMap<>();

        String viewName = controllerV4.process(paramMap, model);

        MyView view = new MyView("/WEB-INF/views/" + viewName + ".jsp");

        view.render(model, request, response);
    }


    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();

        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}
