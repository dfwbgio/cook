package com.mbc.cook.dto.info;

import com.mbc.cook.entity.info.CategoryEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data

public class CategoryDTO {
    Long categorynum;
    String maincategory;
    String subcategory;
    public CategoryEntity entity() {
        return CategoryEntity.builder()
            .categorynum(categorynum)
            .maincategory(maincategory)
            .subcategory(subcategory)
            .build();
    }
}
