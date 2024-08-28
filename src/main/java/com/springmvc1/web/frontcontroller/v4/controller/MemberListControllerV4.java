package com.springmvc1.web.frontcontroller.v4.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import com.springmvc1.domain.member.Member;
import com.springmvc1.domain.member.MemberRepository;
import com.springmvc1.web.frontcontroller.v4.ControllerV4;
import jakarta.servlet.ServletException;

public class MemberListControllerV4 implements ControllerV4 {


    private final MemberRepository memberRepository = MemberRepository.getInstance();


    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model)
            throws ServletException, IOException {

        List<Member> memberList = memberRepository.findAll();

        model.put("members", memberList);

        return "members";
    }
}
