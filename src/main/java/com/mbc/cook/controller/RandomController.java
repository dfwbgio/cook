package com.mbc.cook.controller;

import com.mbc.cook.entity.info.CategoryEntity;
import com.mbc.cook.entity.recipe.RecipeEntity;
import com.mbc.cook.service.random.RandomService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

//추후 혜린
@Slf4j
@Controller
public class RandomController {

    @Autowired
    RandomService randomService;

    @GetMapping(value = "/random/list")
    public String randomList(Model model) {
        model.addAttribute("cssPath", "/random/list");//css 패스 경로(바꾸지X)
        model.addAttribute("pageTitle", "오늘 뭐 먹지?");//타이틀 제목
        return "random/list";
    }
    @PostMapping(value = "/randomResult")
    public void randomResult(HttpServletResponse response) throws IOException {
        List<RecipeEntity> list = randomService.getFood();JSONObject tot = new JSONObject();
        JSONArray arrlist = new JSONArray();
        for (int i = 0; i < list.size(); i++) {
            String food = list.get(i).getFood();
            String image = list.get(i).getImage();
            int recipe_seq = (int) list.get(i).getRecipe_seq();
            JSONObject recipeRandom = new JSONObject();
            recipeRandom.put("food", food);
            recipeRandom.put("image", image);
            recipeRandom.put("recipe_seq", recipe_seq);
            arrlist.add(recipeRandom);
        }

        tot.put("recipeRandom", arrlist);
        String categorydata = tot.toString();
        PrintWriter pp = response.getWriter();
        pp.print(categorydata);
    }
//    @PostMapping(value = "/subCategoryGet")
//    public void subCategoryGet(@RequestParam("category") String category, HttpServletResponse response) throws IOException {
//        List<CategoryEntity> list = infoService.getSubCategoryList(category);
//        JSONObject tot = new JSONObject();
//        JSONArray arrlist = new JSONArray();
//        for (int i = 0; i < list.size(); i++) {
//            String subcategory = list.get(i).getSubcategory();
//            JSONObject recipesubcategory = new JSONObject();
//            recipesubcategory.put("subcategory", subcategory);
//            arrlist.add(recipesubcategory);
//        }
//        tot.put("subcategorylist", arrlist);
//        String categorydata = tot.toString();
//        PrintWriter pp = response.getWriter();
//        pp.print(categorydata);
//    }

}
