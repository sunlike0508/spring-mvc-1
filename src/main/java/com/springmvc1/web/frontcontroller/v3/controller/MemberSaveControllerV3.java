package com.springmvc1.web.frontcontroller.v3.controller;

import java.io.IOException;
import java.util.Map;
import com.springmvc1.domain.member.Member;
import com.springmvc1.domain.member.MemberRepository;
import com.springmvc1.web.frontcontroller.ModelView;
import com.springmvc1.web.frontcontroller.v3.ControllerV3;
import jakarta.servlet.ServletException;

public class MemberSaveControllerV3 implements ControllerV3 {

    private final MemberRepository memberRepository = MemberRepository.getInstance();


    @Override
    public ModelView process(Map<String, String> paramMap) throws ServletException, IOException {

        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        ModelView view = new ModelView("save-result");
        view.getModel().put("member", member);

        return view;
    }
}
