package com.mbc.cook.entity.info;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Setter
@Getter
@SequenceGenerator(
        name = "category_num",//시퀀스 이름X dto 내 아이디랑 같음
        sequenceName = "category_seq",
        allocationSize = 1,
        initialValue = 10000
)

@Table(name="recipecategory")
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
    public CategoryEntity(long categorynum, String maincategory, String subcategory) {
        this.categorynum = categorynum;
        this.maincategory = maincategory;
        this.subcategory = subcategory;
    }
}
