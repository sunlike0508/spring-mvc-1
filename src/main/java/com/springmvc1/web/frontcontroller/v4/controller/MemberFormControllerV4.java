package com.springmvc1.web.frontcontroller.v4.controller;

import java.io.IOException;
import java.util.Map;
import com.springmvc1.web.frontcontroller.v4.ControllerV4;
import jakarta.servlet.ServletException;

public class MemberFormControllerV4 implements ControllerV4 {

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model)
            throws ServletException, IOException {
        return "new-form";
    }
}
