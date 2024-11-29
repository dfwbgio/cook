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
            "WHERE recipeseq = :recipeseq", nativeQuery = true)
    void updateNoImg(@Param("recipeseq") long recipeseq,
                     @Param("category1") String category1,
                     @Param("category2") String category2,
                     @Param("food") String food,
                     @Param("ingredient") String ingredient,
                     @Param("recipe") String recipe);

    @Transactional
    @Query(value = "select recipeseq, category1, category2, " +
            "food, image, ingredient, recipe, hits " +
            "from recipe where food LIKE %:food%", nativeQuery = true)
    List<RecipeEntity> recipeSearchName(@Param("food") String food);

    @Transactional
    @Query(value = "select recipeseq, category1, category2, " +
            "food, image, ingredient, recipe, hits " +
            "from recipe where category1 LIKE %:category1% " +
            "and category2 LIKE %:category2% ", nativeQuery = true)
    List<RecipeEntity> recipeSearchCategory(@Param("category1") String category1, @Param("category2") String category2);

    @Transactional
    @Query(value = "select recipeseq, category1, category2, " +
            "food, image, ingredient, recipe, hits " +
            "from recipe where category1 LIKE %:category1%", nativeQuery = true)
    List<RecipeEntity> recipeSearchOneCategory(@Param("category1") String category1);

    @Transactional
    @Query(value = "select recipeseq, category1, category2, " +
            "food, image, ingredient, recipe, hits " +
            "from recipe where category1 LIKE %:category1% " +
            "and category2 LIKE %:category2% " +
            "and food LIKE %:food% ", nativeQuery = true)
    List<RecipeEntity> recipeSearchAll(@Param("category1") String category1, @Param("category2") String category2, @Param("food") String food);

    @Transactional
    @Query(value = "SELECT * FROM (" +
            "SELECT * FROM recipe " +
            "ORDER BY DBMS_RANDOM.VALUE) " +
            "WHERE ROWNUM <= 10", nativeQuery = true)
    List<RecipeEntity> recipeFindAll();
}
