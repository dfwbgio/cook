package com.mbc.cook.service.recipe;

import com.mbc.cook.entity.recipe.IngreEntity;
import com.mbc.cook.entity.recipe.RecipeEntity;
import com.mbc.cook.repository.recipe.IngreRepository;
import com.mbc.cook.repository.recipe.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeImplement implements RecipeService {
    @Autowired
    RecipeRepository recipeRepository;
    @Autowired
    IngreRepository ingreRepository;

    @Override
    public RecipeEntity select(long num) {
        return recipeRepository.findById(num).orElse(null);
    }

    @Override
    public void clickup(long num) {recipeRepository.clickup(num);}

    @Override
    public IngreEntity findIngredientByID(long num) { return ingreRepository.findById(num).orElse(null); }
}
