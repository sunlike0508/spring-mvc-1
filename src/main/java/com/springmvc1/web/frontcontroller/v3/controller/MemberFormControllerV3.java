package com.springmvc1.web.frontcontroller.v3.controller;

import java.io.IOException;
import java.util.Map;
import com.springmvc1.web.frontcontroller.ModelView;
import com.springmvc1.web.frontcontroller.v3.ControllerV3;
import jakarta.servlet.ServletException;

public class MemberFormControllerV3 implements ControllerV3 {


    @Override
    public ModelView process(Map<String, String> paramMap) throws ServletException, IOException {
        return new ModelView("new-form");
    }
}
