package com.springmvc1.web.frontcontroller.v5.adapter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import com.springmvc1.web.frontcontroller.ModelView;
import com.springmvc1.web.frontcontroller.v3.ControllerV3;
import com.springmvc1.web.frontcontroller.v5.MyHandlerAdapter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ControllerV3HandlerAdapter implements MyHandlerAdapter {

    @Override
    public boolean supports(Object handler) {
        return handler instanceof ControllerV3;
    }


    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object object)
            throws ServletException, IOException {
        ControllerV3 controller = (ControllerV3) object;

        Map<String, String> paramMap = createParamMap(request);

        return controller.process(paramMap);
    }


    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();

        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}
