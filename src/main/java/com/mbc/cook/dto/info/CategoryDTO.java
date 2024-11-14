package com.mbc.cook.dto.info;

import com.mbc.cook.entity.info.CategoryEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class CategoryDTO {
    Long categorynum;
    String maincategory;
    String subcategory;

    public CategoryEntity categoryEntity(){
        return CategoryEntity.builder()//builder는 객체 생성
            .categorynum(categorynum)
            .maincategory(maincategory)
            .subcategory(subcategory)
            .build();
    }
}
