package com.mbc.cook.repository.recipe;

import com.mbc.cook.entity.recipe.RecipeEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<RecipeEntity, Long> {
    @Transactional
    @Modifying
    @Query(value = "update recipe set hits = hits + 1 where recipeseq = :num",nativeQuery = true)
    void clickup(@Param("num") long num);

    @Transactional
    @Modifying
    @Query(value = "insert into cart (cart_seq, id, order_item, status) values (cart_seq.nextval, :id, :ingredient, 'orderprev')",nativeQuery = true)
    void cartSave(@Param("id") String id, @Param("ingredient") String ingredient);

    @Transactional
    @Modifying
    @Query(value = "select order_item from cart where id = :id",nativeQuery = true)
    List<String> selectIngredient(@Param("id") String id);
}
