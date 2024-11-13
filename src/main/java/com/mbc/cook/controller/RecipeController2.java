package com.mbc.cook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

//혜린
@Controller
public class RecipeController2 {
    @GetMapping(value = "/recipe/list")
    public String recipeList(Model model) {

        model.addAttribute("cssPath", "/recipe/list");//css 패스 경로(바꾸지X)
        model.addAttribute("pageTitle", "레시피");//타이틀 제목
        return "recipe/list";
    }

    @GetMapping(value = "/recipe/register")
    public String recipeRegister(Model model) {
        model.addAttribute("cssPath", "/recipe/register");//css 패스 경로(바꾸지X)
        model.addAttribute("pageTitle", "레시피 등록");//타이틀 제목
        return "recipe/register";
    }

    @GetMapping(value = "/recipe/update")
    public String recipeUpdate(Model model) {
        model.addAttribute("cssPath", "/recipe/update");//css 패스 경로(바꾸지X)
        model.addAttribute("pageTitle", "레시피 수정");//타이틀 제목
        return "recipe/update";
    }

    @GetMapping(value = "/recipe/delete")
    public String recipeDelete(Model model) {
        model.addAttribute("cssPath", "/recipe/delete");//css 패스 경로(바꾸지X)
        model.addAttribute("pageTitle", "레시피 삭제");//타이틀 제목
        return "recipe/delete";
    }
}
