package com.mbc.cook.entity.recipe;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@Table(name = "recipe")
@SequenceGenerator(
    name = "recipe_num",//시퀀스 이름X dto 내 아이디랑 같음
    sequenceName = "recipe_seq",
    allocationSize = 1,
    initialValue = 1
)

public class RecipeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "recipe_num")
    @Column()
    long recipeseq;
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
    public RecipeEntity(long recipeseq, String category1, String category2, String image, String food, String ingredient, String recipe, int hits) {
        this.recipeseq = recipeseq;
        this.category1 = category1;
        this.category2 = category2;
        this.image = image;
        this.food = food;
        this.ingredient = ingredient;
        this.recipe = recipe;
        this.hits = hits;
    }
}