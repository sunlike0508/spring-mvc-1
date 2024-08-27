package com.springmvc1.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import com.springmvc1.domain.member.Member;
import com.springmvc1.domain.member.MemberRepository;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet(name = "memberListServlet", urlPatterns = "/servlet/members")
public class MemberListServlet extends HttpServlet {

    private final MemberRepository memberRepository = MemberRepository.getInstance();


    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException {

        List<Member> members = memberRepository.findAll();

        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        PrintWriter printWriter = response.getWriter();

        printWriter.println(members.get(0).getUsername());
        printWriter.println(members.get(0).getAge());
        // html 복사가 잘 안되어서 그냥 출력.
    }
}
