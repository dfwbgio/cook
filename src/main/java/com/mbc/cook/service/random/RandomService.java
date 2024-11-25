package com.mbc.cook.service.random;

import com.mbc.cook.entity.recipe.RecipeEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RandomService {
    List<RecipeEntity> getFood();
}
