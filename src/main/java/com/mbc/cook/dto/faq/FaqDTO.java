package com.mbc.cook.dto.faq;

import com.mbc.cook.entity.faq.FaqEntity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class FaqDTO {
    long faqnum;
    String question;
    String asked;

    public FaqEntity entity() {
        return (new FaqEntity(faqnum,question, asked));
    }
}
