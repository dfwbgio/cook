package com.mbc.cook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

//시아
@Controller
public class MemberController2 {
    @GetMapping(value = "/signup")
    public String memberSignup(Model model) {
        model.addAttribute("cssPath", "/member/signup");//css 패스 경로(바꾸지X)
        model.addAttribute("pageTitle", "회원가입");//타이틀 제목
        return "member/signup";
    }

    @GetMapping(value = "/mypage")
    public String memberMypage(Model model) {
        model.addAttribute("cssPath", "/member/mypage");//css 패스 경로(바꾸지X)
        model.addAttribute("pageTitle", "마이페이지");//타이틀 제목
        return "member/mypage";
    }
}
