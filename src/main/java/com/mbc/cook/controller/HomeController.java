package com.mbc.cook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping(value = "/")
    public String index(Model model) {
        model.addAttribute("cssPath", "/home/index");//css 패스 경로(바꾸지X)
        model.addAttribute("pageTitle", "메인");//타이틀 제목
        return "home/index";
    }
}
