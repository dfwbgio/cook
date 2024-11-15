package com.mbc.cook.service.recipe;

import com.mbc.cook.entity.info.CategoryEntity;
import com.mbc.cook.repository.recipe.RecipeRepository2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RecipeServiceImp2 implements RecipeService2 {
    @Autowired
    RecipeRepository2 RecipeRepository2;
}
