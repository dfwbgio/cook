package com.mbc.cook.service.recipe;

import com.mbc.cook.entity.recipe.IngreEntity;
import com.mbc.cook.entity.recipe.RecipeEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RecipeService2 {
    void recipeRegister(RecipeEntity recipeEntity);

    List<RecipeEntity> recipeSelectAll();

    List<IngreEntity> findIngredient(String ingredient);

    List<IngreEntity> findIngredientAll();
}
