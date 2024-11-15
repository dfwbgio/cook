package com.mbc.cook.repository.info;

import com.mbc.cook.entity.info.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface InfoRepository extends JpaRepository<CategoryEntity,Long> {
    @Override
    List<CategoryEntity> findAll();

    @Transactional
    @Modifying
    @Query(value = "select categorynum, maincategory, subcategory from recipecategory where maincategory=:category", nativeQuery = true)
    List<CategoryEntity> getSubCategoryList(@Param("category") String category);
}
