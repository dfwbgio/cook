package com.mbc.cook.controller;

import com.mbc.cook.dto.faq.FaqDTO;
import com.mbc.cook.entity.faq.FaqEntity;
import com.mbc.cook.service.faq.FaqService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@Slf4j
public class FaqController {
    @Autowired
    FaqService faqService;


    @GetMapping(value = "/faq/list")
    public String faqList(Model model) {
        model.addAttribute("cssPath", "/faq/list");//css 패스 경로(바꾸지X)
        model.addAttribute("pageTitle", "문의");//타이틀 제목
        List<FaqEntity> list = faqService.faqout();
        model.addAttribute("list", list);
        return "faq/list";
    }

    @GetMapping(value = "/faq/register")
    public String faqRegister(Model model) {
        model.addAttribute("cssPath", "/faq/register");//css 패스 경로(바꾸지X)
        model.addAttribute("pageTitle", "문의 등록");//타이틀 제목
        return "faq/register";
    }

    @GetMapping(value = "/faq/update")
    public String faqUpdate(Model model, @RequestParam("num") long num) {
        model.addAttribute("cssPath", "/faq/update");//css 패스 경로(바꾸지X)
        model.addAttribute("pageTitle", "문의 수정");//타이틀 제목
        FaqEntity dto = faqService.updatefind(num);
        model.addAttribute("dto",dto);
        return "faq/update";
    }

    @GetMapping(value = "/faq/delete")
    public String faqDelete(Model model,@RequestParam("num") long num) {
        model.addAttribute("cssPath", "/faq/delete");//css 패스 경로(바꾸지X)
        model.addAttribute("pageTitle", "문의 삭제");//타이틀 제목
        FaqEntity dto =  faqService.deletefind(num);
        model.addAttribute("dto",dto);
        return "faq/delete";
    }

    @GetMapping(value = "/faq/detail")
    public String faqDetail(Model model) {
        model.addAttribute("cssPath", "/faq/detail");//css 패스 경로(바꾸지X)
        model.addAttribute("pageTitle", "문의 상세");//타이틀 제목
        return "faq/detail";
    }

    @PostMapping(value = "/faq/faqsave")
    public String faqsave(FaqDTO dto){
        FaqEntity fentity = dto.entity();
        faqService.insert(fentity);

        return "redirect:/faq/list";
    }

    @PostMapping(value = "/faq/updatesave")
    public String faqupsave(FaqDTO dto){
        FaqEntity faqEntity = dto.entity();
        faqService.update(faqEntity);
        return "redirect:/";
    }
    @PostMapping(value = "/faq/deletesave")
    public String faqdelete(@RequestParam("faqnum") long faqnum){
        faqService.delete(faqnum);
        return "redirect:/";
    }

}
