package com.mbc.cook.controller;

import com.mbc.cook.dto.info.CategoryDTO;
import com.mbc.cook.entity.info.CategoryEntity;
import com.mbc.cook.entity.member.MemberEntity;
import com.mbc.cook.service.info.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
public class InfoController {
    @Autowired
    InfoService infoService;

    @GetMapping(value = "/info/list")
    public String infoList(Model model) {
        model.addAttribute("cssPath", "/info/list");//css 패스 경로(바꾸지X)
        model.addAttribute("pageTitle", "설정");//타이틀 제목
        return "info/list";
    }
    @GetMapping(value = "/info/info")
    public String infoInfo(Model model) {
        model.addAttribute("cssPath", "/info/info");//css 패스 경로(바꾸지X)
        model.addAttribute("pageTitle", "프로젝트 정보");//타이틀 제목
        return "info/info";
    }
    @GetMapping(value = "/info/category")
    public String infoCategory(Model model, @RequestParam(required = false, defaultValue = "0", value = "page") int page) {
//        List<CategoryEntity> list = infoService.getCategory();
        model.addAttribute("cssPath", "/info/category");//css 패스 경로(바꾸지X)
        model.addAttribute("pageTitle", "카테고리 관리");//타이틀 제목
//        model.addAttribute("categoryList", list);

        Page<CategoryEntity> list = infoService.categoryList(page);

        model.addAttribute("npage", page);
        model.addAttribute("total",list.getTotalPages());
        model.addAttribute("categoryList", list.getContent());
        return "info/category";
    }
    @GetMapping(value = "/info/register")
    public String infoRegister(@RequestParam(value = "num") long num, @RequestParam(value = "info") String info, Model model) {
        model.addAttribute("cssPath", "/info/register");//css 패스 경로(바꾸지X)
        model.addAttribute("pageTitle", "카테고리 관리");//타이틀 제목
        model.addAttribute("info", info);
        if(info.equals("register")){
        }
        else{
            CategoryEntity findDTO = infoService.getCategoryList(num);
            model.addAttribute("categoryDto", findDTO);
        }
        return "info/register";
    }
    @PostMapping(value = "/categoryRegister")
    public String categoryRegister(CategoryDTO categoryDTO) {
        CategoryEntity categoryEntity = categoryDTO.categoryEntity();
        infoService.categorySave(categoryEntity);
        return "redirect:/info/category";
    }
    @PostMapping(value = "/categoryUpdate")
    public String categoryUpdate(CategoryDTO categoryDTO) {
        CategoryEntity categoryEntity = categoryDTO.categoryEntity();
        infoService.categoryupdate(categoryEntity);
        return "redirect:/info/category";
    }
    @PostMapping(value = "/categoryDelete")
    public String categoryDelete(@RequestParam(value = "categorynum") long num) {
        infoService.categorydelete(num);
        return "redirect:/info/category";
    }
}