package com.mbc.cook.service.recipe;

import com.mbc.cook.entity.info.CategoryEntity;
import com.mbc.cook.entity.recipe.IngreEntity;
import com.mbc.cook.entity.recipe.RecipeEntity;
import com.mbc.cook.repository.recipe.IngreRepository;
import com.mbc.cook.repository.recipe.RecipeRepository2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RecipeServiceImp2 implements RecipeService2 {
    @Autowired
    RecipeRepository2 recipeRepository2;
    @Autowired
    IngreRepository ingreRepository;

    @Override
    public void recipeRegister(RecipeEntity recipeEntity) {
        recipeRepository2.save(recipeEntity);
    }

    @Override
    public List<IngreEntity> findIngredient(String ingredient) {
        return ingreRepository.findIngredient(ingredient);
    }

    @Override
    public List<IngreEntity> findIngredientAll() {
        return ingreRepository.findAll();
    }

    @Override
    public List<IngreEntity> findIngredientLike(String ingredient) {
        return ingreRepository.findIngredientLike(ingredient);
    }

    @Override
    public void recipeUpdate(long recipeseq, String category1, String category2, String food, String ingredient, String recipe) {
        recipeRepository2.updateNoImg(recipeseq,category1,category2,food,ingredient,recipe);
    }

    @Override
    public void recipeDelete(long recipeseq) {
        recipeRepository2.deleteById(recipeseq);
    }

    @Override
    public Page<RecipeEntity> recipeAllPaging(int page) {
        return recipeRepository2.findAll(PageRequest.of(page,12, Sort.by(Sort.Direction.DESC,"recipeseq")));
    }

    @Override
    public List<RecipeEntity> recipeSearchOneCategory(String category1) {
        return recipeRepository2.recipeSearchOneCategory(category1);
    }

    @Override
    public List<RecipeEntity> recipeSearchCategory(String category1, String category2) {
        return recipeRepository2.recipeSearchCategory(category1,category2);
    }

    @Override
    public List<RecipeEntity> recipeSearchName(String food) {
        return recipeRepository2.recipeSearchName(food);
    }

    @Override
    public List<RecipeEntity> recipeSearchAll(String category1, String category2, String food) {
        return recipeRepository2.recipeSearchAll(category1,category2,food);
    }

    @Override
    public List<RecipeEntity> recipeFindAll() {
        return recipeRepository2.recipeFindAll();
    }
}
