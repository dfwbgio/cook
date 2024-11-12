package com.mbc.cook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InfoController {
    @GetMapping(value = "/info/list")
    public String infoList(Model model) {
        model.addAttribute("cssPath", "/info/list");//css 패스 경로(바꾸지X)
        model.addAttribute("pageTitle", "설정");//타이틀 제목
        return "info/list";
    }
    @GetMapping(value = "/info/info")
    public String infoInfo(Model model) {
        model.addAttribute("cssPath", "/info/info");//css 패스 경로(바꾸지X)
        model.addAttribute("pageTitle", "프로젝트 정보");//타이틀 제목
        return "info/info";
    }
    @GetMapping(value = "/info/category")
    public String infoCategory(Model model) {
        model.addAttribute("cssPath", "/info/category");//css 패스 경로(바꾸지X)
        model.addAttribute("pageTitle", "카테고리 관리");//타이틀 제목
        return "info/category";
    }
}
