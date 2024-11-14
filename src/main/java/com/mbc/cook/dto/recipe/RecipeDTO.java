package com.mbc.cook.dto.recipe;

import com.mbc.cook.entity.recipe.RecipeEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class RecipeDTO {
    long recipe_seq;
    String category1;
    String category2;
    String image;
    String food;
    String ingredient;
    String recipe;
    int hits;

    public RecipeEntity getRecipeEntity(){
        return RecipeEntity.builder()
            .recipe_seq(recipe_seq)
            .category1(category1)
            .category2(category2)
            .image(image)
            .food(food)
            .ingredient(ingredient)
            .recipe(recipe)
            .hits(hits)
            .build();
    }
}