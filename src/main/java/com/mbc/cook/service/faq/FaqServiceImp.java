package com.mbc.cook.service.faq;

import com.mbc.cook.entity.faq.FaqEntity;
import com.mbc.cook.repository.faq.FaqRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FaqServiceImp implements FaqService{
    @Autowired
    FaqRepository faqRepository;
    @Override
    public void insert(FaqEntity fentity) {
        faqRepository.save(fentity);
    }

    @Override
    public List<FaqEntity> faqout() {
        return faqRepository.findAll();
    }

    @Override
    public FaqEntity updatefind(long num) {
        return faqRepository.findById(num).orElse(null);
    }

    @Override
    public void update(FaqEntity faqEntity) {
        faqRepository.save(faqEntity);
    }

    @Override
    public FaqEntity deletefind(long num) {
        return faqRepository.findById(num).orElse(null);
    }

    @Override
    public void delete(long faqnum) {
        faqRepository.deleteById(faqnum);
    }
}
