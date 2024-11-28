package com.mbc.cook.controller;

import com.mbc.cook.entity.info.CategoryEntity;
import com.mbc.cook.entity.recipe.IngreEntity;
import com.mbc.cook.entity.recipe.RecipeEntity;
import com.mbc.cook.service.info.InfoService;
import com.mbc.cook.service.recipe.RecipeInterface;
import com.mbc.cook.service.recipe.RecipeService;
import com.mbc.cook.service.recipe.RecipeService2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

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
        ArrayList<IngreEntity> ingreList = new ArrayList<IngreEntity>();
        ingreList = parseIngredient(ingreList, ing_arr);

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
        ArrayList<IngreEntity> list = new ArrayList<IngreEntity>();
        List<String> ingredientArray = recipeService.selectIngredient(id);
        for (String s : ingredientArray) {
            String[] ingredientNum = s.split(",");
            list = parseIngredient(list, ingredientNum);
        }

        Set<IngreEntity> set = new HashSet<IngreEntity>(list);
        List<Integer> pickList = new ArrayList<>();
        for (IngreEntity str : set) {
            pickList.add(Collections.frequency(list, str));
            System.out.println(str + " : " + Collections.frequency(list, str));
        }
        List<IngreEntity> ingreList = new ArrayList<>(set);

        for (int i=0; i<ingreList.size();i++){
            System.out.println(i+": "+ingreList.get(i).getName());
        }

        System.out.println("picksize: "+pickList);
        model.addAttribute("cartList",ingreList);
        model.addAttribute("picksize",pickList);

        return "recipe/cart";
    }

    public void pathSet(String title,String path, Model model){
        model.addAttribute("cssPath", "/recipe/"+path);//css 패스 경로(바꾸지X)
        model.addAttribute("pageTitle", title);//타이틀 제목
    }

    public ArrayList<IngreEntity> parseIngredient(ArrayList<IngreEntity> list, String [] ing_arr){
        for (String s : ing_arr) {
            list.add(recipeService.findIngredientByID(Long.parseLong(s)));
        }
        return list;
    }
}
