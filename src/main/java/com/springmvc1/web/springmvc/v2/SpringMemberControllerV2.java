package com.springmvc1.web.springmvc.v2;


import java.util.List;
import com.springmvc1.domain.member.Member;
import com.springmvc1.domain.member.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/springmvc/v2/members")
public class SpringMemberControllerV2 {

    private final MemberRepository memberRepository = MemberRepository.getInstance();


    @RequestMapping("/new-form")
    public ModelAndView newForm() {
        return new ModelAndView("new-form");
    }


    @RequestMapping
    public ModelAndView list() {
        List<Member> members = memberRepository.findAll();

        ModelAndView modelAndView = new ModelAndView("members");
        modelAndView.addObject("members", members);
        return modelAndView;
    }


    @RequestMapping("/save")
    public ModelAndView save(HttpServletRequest request) {
        String username = request.getParameter("username");
        String age = request.getParameter("age");

        Member member = new Member(username, Integer.parseInt(age));

        memberRepository.save(member);

        ModelAndView modelAndView = new ModelAndView("save-result");
        modelAndView.addObject("member", member);
        return modelAndView;
    }
}
