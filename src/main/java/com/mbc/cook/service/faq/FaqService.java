package com.mbc.cook.service.faq;

import com.mbc.cook.entity.faq.FaqEntity;

import java.util.List;

public interface FaqService {
    void insert(FaqEntity fentity);

    List<FaqEntity> faqout();

    FaqEntity updatefind(long num);

    void update(FaqEntity faqEntity);

    FaqEntity deletefind(long num);

    void delete(long faqnum);
}
