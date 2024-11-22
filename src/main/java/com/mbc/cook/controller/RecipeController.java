package com.mbc.cook.controller;

import com.mbc.cook.entity.info.CategoryEntity;
import com.mbc.cook.entity.recipe.IngreEntity;
import com.mbc.cook.entity.recipe.RecipeEntity;
import com.mbc.cook.service.info.InfoService;
import com.mbc.cook.service.recipe.RecipeService;
import com.mbc.cook.service.recipe.RecipeService2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

//규리
@Slf4j
@Controller
public class RecipeController {
    @Autowired
    RecipeService recipeService;
    @Autowired
    InfoService infoService;
    @Autowired
    RecipeService2 recipeService2;

    @GetMapping(value = "/recipe/select")
    public String recipeDetail(@RequestParam("num") long num, @RequestParam("path") String path, Model model) {
        RecipeEntity dto = recipeService.select(num);
        if(path.equals("detail")){
            recipeService.clickup(num);
            model.addAttribute("pageTitle", "상세페이지");//타이틀 제목
            model.addAttribute("cssPath", "/recipe/detail");
        }else if(path.equals("update")){
            List<CategoryEntity> recipeList = infoService.getSubCategoryList(dto.getCategory1());
            List<IngreEntity> ingreList = recipeService2.findIngredientAll();
            model.addAttribute("pageTitle", "레시피 수정");//타이틀 제목
            model.addAttribute("cssPath", "/recipe/update");
            model.addAttribute("recipeList", recipeList);
            model.addAttribute("ingreList", ingreList);
        }else if(path.equals("delete")){
            List<CategoryEntity> recipeList = infoService.getSubCategoryList(dto.getCategory1());
            List<IngreEntity> ingreList = recipeService2.findIngredientAll();
            model.addAttribute("pageTitle", "레시피 삭제");//타이틀 제목
            model.addAttribute("cssPath", "/recipe/delete");
            model.addAttribute("recipeList", recipeList);
            model.addAttribute("ingreList", ingreList);
        }
        model.addAttribute("dto",dto);
        return (path.equals("detail")) ? "recipe/detail" : (path.equals("delete")) ? "recipe/delete" : "recipe/update";
    }

    @GetMapping(value = "/recipe/cart")
    public String recipeCart(Model model) {
        model.addAttribute("cssPath", "/recipe/cart");//css 패스 경로(바꾸지X)
        model.addAttribute("pageTitle", "장바구니");//타이틀 제목
        return "recipe/cart";
    }
}
