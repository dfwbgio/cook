package com.mbc.cook.service.recipe;

import com.mbc.cook.entity.info.CategoryEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface RecipeService2 {
    List<CategoryEntity> getCategoryList(String category);
}
