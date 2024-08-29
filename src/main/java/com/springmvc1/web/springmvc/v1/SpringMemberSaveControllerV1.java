package com.springmvc1.web.springmvc.v1;


import com.springmvc1.domain.member.Member;
import com.springmvc1.domain.member.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SpringMemberSaveControllerV1 {

    private final MemberRepository memberRepository = MemberRepository.getInstance();


    @RequestMapping("/springmvc/v1/members/save")
    public ModelAndView process(HttpServletRequest request) {
        String username = request.getParameter("username");
        String age = request.getParameter("age");

        Member member = new Member(username, Integer.parseInt(age));

        memberRepository.save(member);

        ModelAndView modelAndView = new ModelAndView("save-result");
        //modelAndView.getModel().put("member", member);
        modelAndView.addObject("member", member);
        return modelAndView;
    }
}
