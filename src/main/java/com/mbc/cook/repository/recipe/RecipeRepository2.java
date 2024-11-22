package com.mbc.cook.repository.recipe;

import com.mbc.cook.entity.info.CategoryEntity;
import com.mbc.cook.entity.recipe.RecipeEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface RecipeRepository2 extends JpaRepository<RecipeEntity, Long> {
    @Override
    List<RecipeEntity> findAll();

    @Transactional
    @Modifying
    @Query(value = "UPDATE recipe SET category1 = :category1, " +
            "                  category2 = :category2, " +
            "                  food = :food, " +
            "                  ingredient = :ingredient, " +
            "                  recipe = :recipe " +
            "WHERE recipe_seq = :recipeSeq", nativeQuery = true)
    void updateNoImg(@Param("recipeSeq") long recipeSeq,
                     @Param("category1") String category1,
                     @Param("category2") String category2,
                     @Param("food") String food,
                     @Param("ingredient") String ingredient,
                     @Param("recipe") String recipe);
}
