package com.springmvc1.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import com.springmvc1.domain.member.MemberRepository;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet(name = "memberFormServlet", urlPatterns = "/servlet/members/new-form")
public class MemberFormServlet extends HttpServlet {

    private final MemberRepository memberRepository = MemberRepository.getInstance();


    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        PrintWriter w = response.getWriter();
        w.write("<!DOCTYPE html>\n" + "<html>\n" + "<head>\n" + "    <meta charset=\"UTF-8\">\n"
                + "    <title>Title</title>\n" + "</head>\n" + "<body>\n"
                + "<form action=\"/servlet/members/save\" method=\"post\">\n"
                + "    username: <input type=\"text\" name=\"username\" />\n"
                + "    age:      <input type=\"text\" name=\"age\" />\n" + " <button type=\"submit\">전송</button>\n"
                + "</form>\n" + "</body>\n" + "</html>\n");
    }
}
