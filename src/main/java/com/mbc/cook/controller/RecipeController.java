package com.mbc.cook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

//규리
@Controller
public class RecipeController {
    @GetMapping(value = "/recipe/detail")
    public String recipeDetail(Model model) {
        model.addAttribute("cssPath", "/recipe/detail");//css 패스 경로(바꾸지X)
        model.addAttribute("pageTitle", "레시피 상세");//타이틀 제목
        return "recipe/detail";
    }

    @GetMapping(value = "/recipe/cart")
    public String recipeCart(Model model) {
        model.addAttribute("cssPath", "/recipe/cart");//css 패스 경로(바꾸지X)
        model.addAttribute("pageTitle", "장바구니");//타이틀 제목
        return "recipe/cart";
    }
}
