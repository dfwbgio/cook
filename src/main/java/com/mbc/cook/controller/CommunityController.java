package com.mbc.cook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

//민성
@Controller
public class CommunityController {
    @GetMapping(value = "/community/list")
    public String communityList(Model model) {
        model.addAttribute("cssPath", "/community/list");//css 패스 경로(바꾸지X)
        model.addAttribute("pageTitle", "커뮤니티");//타이틀 제목
        return "community/list";
    }

    @GetMapping(value = "/community/register")
    public String communityRegister(Model model) {
        model.addAttribute("cssPath", "/community/register");//css 패스 경로(바꾸지X)
        model.addAttribute("pageTitle", "커뮤니티 등록");//타이틀 제목
        return "community/register";
    }

    @GetMapping(value = "/community/update")
    public String communityUpdate(Model model) {
        model.addAttribute("cssPath", "/community/update");//css 패스 경로(바꾸지X)
        model.addAttribute("pageTitle", "커뮤니티 수정");//타이틀 제목
        return "community/update";
    }

    @GetMapping(value = "/community/delete")
    public String communityDelete(Model model) {
        model.addAttribute("cssPath", "/community/delete");//css 패스 경로(바꾸지X)
        model.addAttribute("pageTitle", "커뮤니티 삭제");//타이틀 제목
        return "community/delete";
    }

    @GetMapping(value = "/community/detail")
    public String communityDetail(Model model) {
        model.addAttribute("cssPath", "/community/detail");//css 패스 경로(바꾸지X)
        model.addAttribute("pageTitle", "커뮤니티 상세");//타이틀 제목
        return "community/detail";
    }
}
