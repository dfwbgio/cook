package com.mbc.cook.repository.random;

import com.mbc.cook.entity.recipe.RecipeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RandomRepository extends JpaRepository<RecipeEntity,Long> {
}
