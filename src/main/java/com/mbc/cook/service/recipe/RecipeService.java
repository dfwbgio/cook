package com.mbc.cook.service.recipe;

import com.mbc.cook.entity.recipe.CartEntity;
import com.mbc.cook.entity.recipe.IngreEntity;
import com.mbc.cook.entity.recipe.RecipeEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RecipeService {

    RecipeEntity select(long num);

    void clickup(long num);

    IngreEntity findIngredientByID(long num);

    long findCartByID(String id);

    void cartSave(String id, String ingredient);

    void cartUpdate(String id, String ingredient);

    String selectIngredient(String id, String status);

    void ingredientDelete(String ingreString, String id);
}
