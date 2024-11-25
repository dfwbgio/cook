package com.mbc.cook.entity.recipe;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@Table(name = "ingredient")

public class IngreEntity {
    @Id
    @Column
    long ingre_seq;
    @Column
    String name;
    @Column
    int price;
    @Column
    String keyword;

    @Builder
    public IngreEntity(Long ingre_seq, String name, int price, String keyword) {
        this.ingre_seq = ingre_seq;
        this.name = name;
        this.price = price;
        this.keyword = keyword;
    }
}
