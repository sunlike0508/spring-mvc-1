package com.springmvc1.web.servletmvc;


import java.io.IOException;
import com.springmvc1.domain.member.Member;
import com.springmvc1.domain.member.MemberRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "mvcMemberSaveServlet", urlPatterns = "/servlet-mvc/members/save")
public class MvcMemberSaveServlet extends HttpServlet {

    private final MemberRepository memberRepository = MemberRepository.getInstance();


    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String username = request.getParameter("username");
        String age = request.getParameter("age");

        Member member = new Member(username, Integer.parseInt(age));

        Member savedMember = memberRepository.save(member);

        request.setAttribute("member", member);

        String viewPath = "/WEB-INF/views/save-result.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }
}
