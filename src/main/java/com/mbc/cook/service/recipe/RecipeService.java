package com.mbc.cook.service.recipe;

import com.mbc.cook.entity.recipe.IngreEntity;
import com.mbc.cook.entity.recipe.RecipeEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RecipeService {

    RecipeEntity select(long num);

    void clickup(long num);

    IngreEntity findIngredientByID(long num);
}
