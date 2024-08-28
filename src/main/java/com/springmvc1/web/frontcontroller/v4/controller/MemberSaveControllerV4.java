package com.springmvc1.web.frontcontroller.v4.controller;

import java.io.IOException;
import java.util.Map;
import com.springmvc1.domain.member.Member;
import com.springmvc1.domain.member.MemberRepository;
import com.springmvc1.web.frontcontroller.v4.ControllerV4;
import jakarta.servlet.ServletException;

public class MemberSaveControllerV4 implements ControllerV4 {

    private final MemberRepository memberRepository = MemberRepository.getInstance();


    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model)
            throws ServletException, IOException {

        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        model.put("member", member);

        return "save-result";
    }
}
