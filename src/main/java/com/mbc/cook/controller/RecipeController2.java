package com.mbc.cook.controller;

import com.mbc.cook.dto.recipe.RecipeDTO;
import com.mbc.cook.entity.info.CategoryEntity;
import com.mbc.cook.entity.recipe.IngreEntity;
import com.mbc.cook.entity.recipe.RecipeEntity;
import com.mbc.cook.service.info.InfoService;
import com.mbc.cook.service.recipe.RecipeService2;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.UUID;
@Slf4j

//혜린
@Controller
public class RecipeController2 {
    String imgPath = "C:\\project\\cook\\src\\main\\resources\\static\\image\\upload";

    @Autowired
    InfoService infoService;
    @Autowired
    RecipeService2 recipeService2;

    @GetMapping(value = "/recipe/list")
    public String recipeList(Model model) {
        List<RecipeEntity> list = recipeService2.recipeSelectAll();
        model.addAttribute("cssPath", "/recipe/list");//css 패스 경로(바꾸지X)
        model.addAttribute("pageTitle", "레시피");//타이틀 제목
        model.addAttribute("recipeList", list);
        return "recipe/list";
    }

    @GetMapping(value = "/recipe/register")
    public String recipeRegister(Model model) {
        List<IngreEntity> list = recipeService2.findIngredientAll();
        model.addAttribute("cssPath", "/recipe/register");//css 패스 경로(바꾸지X)
        model.addAttribute("pageTitle", "레시피 등록");//타이틀 제목
        model.addAttribute("ingreList", list);//타이틀 제목
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
    public void subCategoryGet(@RequestParam("category") String category, HttpServletResponse response) throws IOException {
        List<CategoryEntity> list = infoService.getSubCategoryList(category);
        JSONObject tot = new JSONObject();
        JSONArray arrlist = new JSONArray();
        for (int i = 0; i < list.size(); i++) {
            String subcategory = list.get(i).getSubcategory();
            JSONObject recipesubcategory = new JSONObject();
            recipesubcategory.put("subcategory", subcategory);
            arrlist.add(recipesubcategory);
        }
        tot.put("subcategorylist", arrlist);
        String categorydata = tot.toString();
        PrintWriter pp = response.getWriter();
        pp.print(categorydata);
    }

    //레시피 등록
    @PostMapping(value = "recipeRegister")
    public String recipeRegister(RecipeDTO dto, MultipartHttpServletRequest multi) throws IOException {
        //이미지
        MultipartFile mf = multi.getFile("image");
        String fileName = mf.getOriginalFilename();
        UUID uu = UUID.randomUUID();
        fileName = uu.toString() + "_" + fileName;
        dto.setImage1(fileName);
        mf.transferTo(new File(imgPath + "\\" + fileName));
        RecipeEntity recipeEntity = dto.getRecipeEntity();
        recipeService2.recipeRegister(recipeEntity);
        return "redirect:/recipe/list";
    }

    @PostMapping(value = "/recipeUpdate")
    public String recipeUpdate(RecipeDTO dto,
                               @RequestParam("recipe_seq") long recipe_seq,
                               @RequestParam("category1") String category1,
                               @RequestParam("category2") String category2,
                               @RequestParam("image") MultipartFile image, // 수정
                               @RequestParam("food") String food,
                               @RequestParam("ingredient") String ingredient,
                               @RequestParam("recipe") String recipe,
                               @RequestParam("or_img") String or_img,
                               MultipartHttpServletRequest multi,
                               Model model) throws IOException {
        if (image.isEmpty()) { // 이미지 변경X
            recipeService2.recipeUpdate(recipe_seq, category1, category2, food, ingredient, recipe);
        } else { // 이미지 변경O
            MultipartFile mf = multi.getFile("image");
            String fileName = mf.getOriginalFilename();
            UUID uu = UUID.randomUUID();
            fileName = uu.toString() + "_" + fileName;
            dto.setImage1(fileName);
            mf.transferTo(new File(imgPath + "\\" + fileName));
            RecipeEntity recipeEntity = dto.getRecipeEntity();
            recipeService2.recipeRegister(recipeEntity);

            // 기존 이미지 삭제
            File origin = new File(imgPath + "\\" + or_img);
            if (origin.exists()) {
                origin.delete();
            }
        }
        return "redirect:/recipe/list";
    }


    //크롤링
    @PostMapping(value = "ingreCrolling", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public void ingreCrolling(@RequestBody Map<String, String> requestData, HttpServletResponse response) throws IOException {
        String ingredient = requestData.get("ingredient");  // JSON에서 ingredient 값을 추출
        JSONObject tot = new JSONObject();
        JSONArray arrlist = new JSONArray();
        String txt = "";
        try {
            // Python 스크립트 실행
            String path = "C:\\project\\cook\\coupangCrolling.py";
            ProcessBuilder builder = new ProcessBuilder("python", path, ingredient);
            // 프로세스 실행
            Process process = builder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), "UTF-8"));
            StringBuilder resultBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                resultBuilder.append(line);
            }
            if (resultBuilder.length() > 0) {
                List<IngreEntity> list = recipeService2.findIngredient(ingredient);
                for (int i = 0; i < list.size(); i++) {
                    Long ingre_seq = list.get(i).getIngre_seq();
                    String ingrename = list.get(i).getName();
                    int ingreprice = list.get(i).getPrice();
                    String keyword = list.get(i).getKeyword();
                    JSONObject recipeingredient = new JSONObject();
                    recipeingredient.put("ingreseq", ingre_seq);
                    recipeingredient.put("ingrename", ingrename);
                    recipeingredient.put("ingreprice", ingreprice);
                    recipeingredient.put("keyword", keyword);
                    arrlist.add(recipeingredient);
                }
                tot.put("ingredientlist", arrlist);
                String ingredata = tot.toString();
                txt = ingredata;
            } else {
                txt = "python script에 데이터가 없습니다...T.T";
            }
        } catch (Exception e) {
            e.printStackTrace();
            txt = e.getMessage();
        }
        PrintWriter pp = response.getWriter();
        pp.print(txt);
    }

    //불러오기
    @PostMapping(value = "/ingreFind")
    public void ingreFind(@RequestParam("ingredient") String ingredient, HttpServletResponse response) throws IOException {
        List<IngreEntity> list = recipeService2.findIngredientLike(ingredient);
        JSONObject tot = new JSONObject();
        JSONArray arrlist = new JSONArray();
        for (int i = 0; i < list.size(); i++) {
            Long ingre_seq = list.get(i).getIngre_seq();
            String ingrename = list.get(i).getName();
            int ingreprice = list.get(i).getPrice();
            JSONObject recipeingredient = new JSONObject();
            recipeingredient.put("ingreseq", ingre_seq);
            recipeingredient.put("ingrename", ingrename);
            recipeingredient.put("ingreprice", ingreprice);
            arrlist.add(recipeingredient);
        }
        tot.put("ingredientlist", arrlist);
        String ingredata = tot.toString();
        PrintWriter pp = response.getWriter();
        pp.print(ingredata);
    }

    @PostMapping(value = "/recipeDelete")
    public String recipeDelete(@RequestParam(value = "recipe_seq") long recipe_seq,
                               @RequestParam(value = "image") String image){
        recipeService2.recipeDelete(recipe_seq);
        // 기존 이미지 삭제
        File origin = new File(imgPath + "\\" + image);
        origin.delete();
        return "redirect:/recipe/list";
    }
}