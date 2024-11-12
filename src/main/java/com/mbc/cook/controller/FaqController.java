package com.mbc.cook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FaqController {
    @GetMapping(value = "/faq/list")
    public String faqList(Model model) {
        model.addAttribute("cssPath", "/faq/list");//css 패스 경로(바꾸지X)
        model.addAttribute("pageTitle", "문의");//타이틀 제목
        return "faq/list";
    }

    @GetMapping(value = "/faq/register")
    public String faqRegister(Model model) {
        model.addAttribute("cssPath", "/faq/register");//css 패스 경로(바꾸지X)
        model.addAttribute("pageTitle", "문의 등록");//타이틀 제목
        return "faq/register";
    }

    @GetMapping(value = "/faq/update")
    public String faqUpdate(Model model) {
        model.addAttribute("cssPath", "/faq/update");//css 패스 경로(바꾸지X)
        model.addAttribute("pageTitle", "문의 수정");//타이틀 제목
        return "faq/update";
    }

    @GetMapping(value = "/faq/delete")
    public String faqDelete(Model model) {
        model.addAttribute("cssPath", "/faq/delete");//css 패스 경로(바꾸지X)
        model.addAttribute("pageTitle", "문의 삭제");//타이틀 제목
        return "faq/delete";
    }

    @GetMapping(value = "/faq/detail")
    public String faqDetail(Model model) {
        model.addAttribute("cssPath", "/faq/detail");//css 패스 경로(바꾸지X)
        model.addAttribute("pageTitle", "문의 상세");//타이틀 제목
        return "faq/detail";
    }
}
