package com.mbc.cook.controller;

import com.mbc.cook.entity.recipe.RecipeEntity;
import com.mbc.cook.service.recipe.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

//규리
@Controller
public class RecipeController {
    @Autowired
    RecipeService recipeService;

    @GetMapping(value = "/recipe/select")
    public String recipeDetail(@RequestParam("num") long num, @RequestParam("path") String path, Model model) {
        RecipeEntity dto = recipeService.select(num);
        if(path.equals("detail")){ recipeService.clickup(num);}
        model.addAttribute("dto",dto);
        model.addAttribute("cssPath", "/recipe/detail");//css 패스 경로(바꾸지X)
        model.addAttribute("pageTitle", "상세페이지");//타이틀 제목
        return (path.equals("detail")) ? "recipe/detail" : (path.equals("delete")) ? "recipe/delete" : "recipe/update";
    }

    @GetMapping(value = "/recipe/cart")
    public String recipeCart(Model model) {
        model.addAttribute("cssPath", "/recipe/cart");//css 패스 경로(바꾸지X)
        model.addAttribute("pageTitle", "장바구니");//타이틀 제목
        return "recipe/cart";
    }
}
