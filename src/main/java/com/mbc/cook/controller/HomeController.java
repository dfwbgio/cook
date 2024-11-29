package com.mbc.cook.controller;

import com.mbc.cook.entity.recipe.RecipeEntity;
import com.mbc.cook.service.recipe.RecipeService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    RecipeService2 recipeService2;

    @GetMapping(value = "/")
    public String index(Model model) {
        List<RecipeEntity> recipeList = recipeService2.recipeFindAll();
        model.addAttribute("cssPath", "/home/index");//css 패스 경로(바꾸지X)
        model.addAttribute("pageTitle", "메인");//타이틀 제목
        model.addAttribute("recipeList", recipeList);
        return "home/index";
    }
}
