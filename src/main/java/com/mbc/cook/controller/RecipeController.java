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
import org.springframework.web.bind.annotation.PostMapping;
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
        //재료 ID -> 정보 가져와서 저장
        String [] ing_arr = dto.getIngredient().split(",");
        IngreEntity [] ingreList = parseIngredient(ing_arr);

        List<CategoryEntity> recipeList = infoService.getSubCategoryList(dto.getCategory1()); //삭제/수정 시 소분류 불러오기
        model.addAttribute("recipeList", recipeList);model.addAttribute("ingreList", ingreList);//저장된 재료들 숫자 → 이름 변환
        model.addAttribute("dto",dto);

        if(path.equals("detail")){
            pathSet("상세페이지",path, model);
            recipeService.clickup(num);
        }else if(path.equals("update")){
            pathSet("레시피 수정",path, model);
            List<IngreEntity> ingreList2 = recipeService2.findIngredientAll();
            model.addAttribute("ingreList2",ingreList2);//모든 재료 불러오기
        }else if(path.equals("delete")){
            pathSet("레시피 삭제",path, model);
        }
        return (path.equals("detail")) ? "recipe/detail" : (path.equals("delete")) ? "recipe/delete" : "recipe/update";
    }

    @PostMapping(value="/recipe/cartsave")
    public String cartSave(@RequestParam("id") String id, @RequestParam("ingredient") String ingredient){
        recipeService.cartSave(id, ingredient);
        return "redirect:/recipe/list";
    }

    @GetMapping(value = "/recipe/cart")
    public String recipeCart(@RequestParam("id") String id, Model model) {
        pathSet("장바구니","cart", model);
        List<String> ingredientArray = recipeService.selectIngredient(id);
        for (int i=0; i<ingredientArray.size(); i++){
//            String [] ingreList = ingredientArray[i].split(",");

        }

        return "recipe/cart";
    }

    public void pathSet(String title,String path, Model model){
        model.addAttribute("cssPath", "/recipe/"+path);//css 패스 경로(바꾸지X)
        model.addAttribute("pageTitle", title);//타이틀 제목
    }

    public IngreEntity[] parseIngredient(String [] ing_arr){
        IngreEntity [] ingreList = new IngreEntity[ing_arr.length];
        for(int i=0;i<ing_arr.length;i++){
            ingreList[i] = recipeService.findIngredientByID(Long.parseLong(ing_arr[i]));
        }
        return ingreList;
    }
}
