package com.springmvc1.web.frontcontroller.v5;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.springmvc1.web.frontcontroller.ModelView;
import com.springmvc1.web.frontcontroller.MyView;
import com.springmvc1.web.frontcontroller.v3.controller.MemberFormControllerV3;
import com.springmvc1.web.frontcontroller.v3.controller.MemberListControllerV3;
import com.springmvc1.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import com.springmvc1.web.frontcontroller.v4.controller.MemberFormControllerV4;
import com.springmvc1.web.frontcontroller.v4.controller.MemberListControllerV4;
import com.springmvc1.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import com.springmvc1.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;
import com.springmvc1.web.frontcontroller.v5.adapter.ControllerV4HandlerAdapter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "frontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {

    private final Map<String, Object> handlerMappingMap = new HashMap<>();
    private final List<MyHandlerAdapter> handlerAdapterList = new ArrayList<>();


    public FrontControllerServletV5() {
        initHandlerMappingMap();
        initHandlerAdapterList();
    }


    private void initHandlerAdapterList() {
        handlerAdapterList.add(new ControllerV3HandlerAdapter());
        handlerAdapterList.add(new ControllerV4HandlerAdapter());
    }


    private void initHandlerMappingMap() {
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());
        handlerMappingMap.put("/front-controller/v5/v4/members/new-form", new MemberFormControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members/save", new MemberSaveControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members", new MemberListControllerV4());
    }


    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("FrontControllerServletV5.service : " + request.getRequestURI());

        Object handler = getHandler(request);

        if(handler == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        MyHandlerAdapter myHandlerAdapter = findHandler(handler);

        ModelView mv = myHandlerAdapter.handle(request, response, handler);

        MyView view = new MyView("/WEB-INF/views/" + mv.getViewName() + ".jsp");

        view.render(mv.getModel(), request, response);
    }


    private MyHandlerAdapter findHandler(Object handler) {
        MyHandlerAdapter findHandler;

        for(MyHandlerAdapter adapter : handlerAdapterList) {
            if(adapter.supports(handler)) {
                return adapter;
            }
        }

        throw new IllegalArgumentException("핸들러 못 찾음");
    }


    private Object getHandler(HttpServletRequest request) {
        String requestURI = request.getRequestURI();

        return handlerMappingMap.get(requestURI);
    }


    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();

        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}
