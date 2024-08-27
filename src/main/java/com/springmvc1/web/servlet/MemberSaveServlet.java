package com.springmvc1.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import com.springmvc1.domain.member.Member;
import com.springmvc1.domain.member.MemberRepository;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet(name = "memberSaveServlet", urlPatterns = "/servlet/members/save")
public class MemberSaveServlet extends HttpServlet {

    private final MemberRepository memberRepository = MemberRepository.getInstance();


    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String username = request.getParameter("username");
        String age = request.getParameter("age");

        Member member = new Member(username, Integer.parseInt(age));

        Member savedMember = memberRepository.save(member);

        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        PrintWriter printWriter = response.getWriter();
        printWriter.write(
                "<html>\n" + "<head>\n" + " <meta charset=\"UTF-8\">\n" + "</head>\n" + "<body>\n" + "성공\n" + "<ul>\n"
                        + "    <li>id=" + member.getId() + "</li>\n" + "    <li>username=" + member.getUsername()
                        + "</li>\n" + " <li>age=" + member.getAge() + "</li>\n" + "</ul>\n"
                        + "<a href=\"/index.html\">메인</a>\n" + "</body>\n" + "</html>");
    }
}
