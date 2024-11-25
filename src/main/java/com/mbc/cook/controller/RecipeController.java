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
    public String recipeDetail(@RequestParam("num") long num, @RequestParam("path") String path, Model model,List<IngreEntity> ingreList) {
        RecipeEntity dto = recipeService.select(num);
        //선택된 재료 갖고 오기
        String [] ing_arr = dto.getIngredient().split(",");
        for(int i=0;i<ing_arr.length;i++){
            IngreEntity ingre = recipeService.findIngredientByID(Long.parseLong(ing_arr[i]));
            ingreList.add(ingre);
        }

        if(path.equals("detail")){
//            model.addAttribute("pageTitle", "상세페이지");//타이틀 제목
            recipeService.clickup(num);
            pathSet("상세페이지",path, model);
        }else if(path.equals("update")){
//            model.addAttribute("pageTitle", "레시피 수정");//타이틀 제목
            List<IngreEntity> ingreList2 = recipeService2.findIngredientAll();
            pathSet("레시피 수정",path, model);
            model.addAttribute("ingreList2",ingreList2);//모든 재료 불러오기
        }else if(path.equals("delete")){
            pathSet("레시피 삭제",path, model);
//            model.addAttribute("pageTitle", "레시피 삭제");//타이틀 제목
        }
//        model.addAttribute("cssPath", "/recipe/"+path);

        List<CategoryEntity> recipeList = infoService.getSubCategoryList(dto.getCategory1()); //삭제/수정 시 소분류 불러오기
        model.addAttribute("recipeList", recipeList);
        model.addAttribute("ingreList", ingreList);//저장된 재료들 변환

        model.addAttribute("dto",dto);
        return (path.equals("detail")) ? "recipe/detail" : (path.equals("delete")) ? "recipe/delete" : "recipe/update";
    }

    @GetMapping(value = "/recipe/cart")
    public String recipeCart(Model model) {
        pathSet("장바구니","cart", model);
        return "recipe/cart";
    }
    public void pathSet(String title,String path, Model model){
        model.addAttribute("cssPath", "/recipe/"+path);//css 패스 경로(바꾸지X)
        model.addAttribute("pageTitle", title);//타이틀 제목
    }
}
