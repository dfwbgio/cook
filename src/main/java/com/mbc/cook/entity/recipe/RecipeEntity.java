package com.mbc.cook.entity.recipe;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@Table(name = "recipe")

public class RecipeEntity {
    @Id
    @Column
    long recipe_seq;
    @Column
    String category1;
    @Column
    String category2;
    @Column
    String food;
    @Column
    String image;
    @Column
    String ingredient;
    @Column
    String recipe;
    @Column
    int hits;

    @Builder
    public RecipeEntity(long recipe_seq, String category1, String category2, String image, String food, String ingredient, String recipe, int hits) {
        this.recipe_seq = recipe_seq;
        this.category1 = category1;
        this.category2 = category2;
        this.image = image;
        this.food = food;
        this.ingredient = ingredient;
        this.recipe = recipe;
        this.hits = hits;
    }
}