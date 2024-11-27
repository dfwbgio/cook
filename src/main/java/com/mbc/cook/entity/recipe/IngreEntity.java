package com.mbc.cook.entity.recipe;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@Table(name = "ingredient")
@SequenceGenerator(
    name = "ingre_num",//시퀀스 이름X dto 내 아이디랑 같음
    sequenceName = "ingre_seq",
    allocationSize = 1,
    initialValue = 1
)

public class IngreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "ingre_num")
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
