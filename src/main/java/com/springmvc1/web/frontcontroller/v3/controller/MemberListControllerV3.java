package com.springmvc1.web.frontcontroller.v3.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import com.springmvc1.domain.member.Member;
import com.springmvc1.domain.member.MemberRepository;
import com.springmvc1.web.frontcontroller.ModelView;
import com.springmvc1.web.frontcontroller.v3.ControllerV3;
import jakarta.servlet.ServletException;

public class MemberListControllerV3 implements ControllerV3 {


    private final MemberRepository memberRepository = MemberRepository.getInstance();


    @Override
    public ModelView process(Map<String, String> paramMap) throws ServletException, IOException {

        List<Member> memberList = memberRepository.findAll();

        ModelView view = new ModelView("members");

        view.getModel().put("members", memberList);

        return view;
    }
}