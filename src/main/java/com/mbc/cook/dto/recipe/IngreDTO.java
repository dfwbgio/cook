package com.mbc.cook.dto.recipe;

import com.mbc.cook.entity.recipe.IngreEntity;
import com.mbc.cook.entity.recipe.RecipeEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class IngreDTO {
    long ingre_seq;
    String name;
    int price;
    String keyword;

    public IngreEntity getIngreEntity(){
        return IngreEntity.builder()
            .ingre_seq(ingre_seq)
            .name(name)
            .price(price)
            .keyword(keyword)
            .build();
    }
}
