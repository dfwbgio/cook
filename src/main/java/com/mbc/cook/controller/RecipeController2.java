package com.mbc.cook.controller;

import com.mbc.cook.entity.info.CategoryEntity;
import com.mbc.cook.entity.recipe.RecipeEntity;
import com.mbc.cook.service.info.InfoInterface;
import com.mbc.cook.service.info.InfoService;
import com.mbc.cook.service.recipe.RecipeService2;
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
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Slf4j
//혜린
@Controller
public class RecipeController2 {
    @Autowired
    InfoService infoService;
    @Autowired
    RecipeService2 recipeService2;

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

    //레시피 리스트 및 등록 시 카테고리1 클릭하면 동작
    @PostMapping(value = "/subCategoryGet")
    @ResponseBody
    public void subCategoryGet(@RequestParam("category") String category, HttpServletResponse response) throws IOException {
        List<CategoryEntity> list = infoService.getSubCategoryList(category);
        JSONObject tot = new JSONObject();
        JSONArray arrlist = new JSONArray();
        for(int i=0;i<list.size();i++)
        {
            String subcategory = list.get(i).getSubcategory();
            JSONObject recipesubcategory = new JSONObject();
            recipesubcategory.put("subcategory", subcategory);
            arrlist.add(recipesubcategory);
        }
        tot.put("subcategorylist",arrlist);
        String categorydata= tot.toString();
        PrintWriter pp = response.getWriter();
        pp.print(categorydata);
    }
}
