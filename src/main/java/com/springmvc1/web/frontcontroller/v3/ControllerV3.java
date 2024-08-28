package com.springmvc1.web.frontcontroller.v3;

import java.io.IOException;
import java.util.Map;
import com.springmvc1.web.frontcontroller.ModelView;
import jakarta.servlet.ServletException;

public interface ControllerV3 {

    ModelView process(Map<String, String> paramMap) throws ServletException, IOException;
}
