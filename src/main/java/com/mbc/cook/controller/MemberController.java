package com.mbc.cook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

//현준
@Controller
public class MemberController {
    @GetMapping(value = "/login")
    public String memberLogin(Model model) {
        model.addAttribute("cssPath", "/member/login");//css 패스 경로(바꾸지X)
        model.addAttribute("pageTitle", "로그인");//타이틀 제목
        return "member/login";
    }

    @GetMapping(value = "/member/list")
    public String memberList(Model model) {
        model.addAttribute("cssPath", "/member/list");//css 패스 경로(바꾸지X)
        model.addAttribute("pageTitle", "회원 관리");//타이틀 제목
        return "member/list";
    }
}
