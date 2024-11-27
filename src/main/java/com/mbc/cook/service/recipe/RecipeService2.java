package com.mbc.cook.service.recipe;

import com.mbc.cook.entity.info.CategoryEntity;
import com.mbc.cook.entity.recipe.IngreEntity;
import com.mbc.cook.entity.recipe.RecipeEntity;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RecipeService2 {
    void recipeRegister(RecipeEntity recipeEntity);
//    List<RecipeEntity> recipeSelectAll();
    List<IngreEntity> findIngredient(String ingredient);

    List<IngreEntity> findIngredientAll();

    List<IngreEntity> findIngredientLike(String ingredient);

    void recipeUpdate(long recipeseq, String category1, String category2, String food, String ingredient, String recipe);

    void recipeDelete(long recipeseq);

    Page<RecipeEntity> recipeAllPaging(int page);

    List<RecipeEntity> recipeSearchName(String food);

    List<RecipeEntity> recipeSearchCategory(String category1, String category2);

    List<RecipeEntity> recipeSearchAll(String category1, String category2, String food);

    List<RecipeEntity> recipeSearchOneCategory(String category1);
}
