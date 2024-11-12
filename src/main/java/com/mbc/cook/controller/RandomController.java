package com.mbc.cook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

//추후 혜린
@Controller
public class RandomController {
    @GetMapping(value = "/random/list")
    public String randomList(Model model) {
        model.addAttribute("cssPath", "/random/list");//css 패스 경로(바꾸지X)
        model.addAttribute("pageTitle", "오늘 뭐 먹지?");//타이틀 제목
        return "random/list";
    }
}
