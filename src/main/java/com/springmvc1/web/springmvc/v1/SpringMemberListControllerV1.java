package com.springmvc1.web.springmvc.v1;


import java.util.List;
import com.springmvc1.domain.member.Member;
import com.springmvc1.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SpringMemberListControllerV1 {

    private final MemberRepository memberRepository = MemberRepository.getInstance();


    @RequestMapping("/springmvc/v1/members")
    public ModelAndView process() {
        List<Member> members = memberRepository.findAll();

        ModelAndView modelAndView = new ModelAndView("members");
        modelAndView.addObject("members", members);
        return modelAndView;
    }
}
