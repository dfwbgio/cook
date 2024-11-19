package com.mbc.cook.controller;

import com.mbc.cook.dto.member.MemberDTO;
import com.mbc.cook.service.member.MemberService2;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.io.PrintWriter;

//시아
@Controller
public class MemberController2 {
    @Autowired
    MemberService2 memberService2;

    @GetMapping(value = "/signup")
    public String memberSignup(MemberDTO memberDTO,Model model) {
        model.addAttribute("cssPath", "/member/signup");//css 패스 경로(바꾸지X)
        model.addAttribute("pageTitle", "회원가입");//타이틀 제목
        model.addAttribute("memberdto",memberDTO);
        return "member/signup";
    }

    @PostMapping(value = "/usersave")
    public String memberSave(
            @ModelAttribute("memberDTO") @Valid MemberDTO memberDTO, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "member/signup";
        } else {
            memberService2.insert(memberDTO);
            return "redirect:/";
        }
    }

    @PostMapping(value = "/idcheck")
    public void idCheck(@RequestParam("id") String id, HttpServletResponse response) throws IOException {
        String result = memberService2.idCheck(id);
        PrintWriter pp = response.getWriter();
        pp.print(result);
    }

    @GetMapping(value = "/mypage")
    public String memberMypage(Model model) {
        model.addAttribute("cssPath", "/member/mypage");//css 패스 경로(바꾸지X)
        model.addAttribute("pageTitle", "마이페이지");//타이틀 제목
        return "member/mypage";
    }
}
