package com.mbc.cook.entity.info;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@Data
@SequenceGenerator(
        name = "category_num",//시퀀스 이름X dto 내 아이디랑 같음
        sequenceName = "category_seq",
        allocationSize = 1,
        initialValue = 1
)

@Table(name="recipecategory")
@Entity
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "category_num")
    @Column
    Long categorynum;
    @Column
    String maincategory;
    @Column
    String subcategory;

    @Builder
    public CategoryEntity(Long categorynum, String maincategory, String subcategory) {
        this.categorynum = categorynum;
        this.maincategory = maincategory;
        this.subcategory = subcategory;
    }
}
