package com.mbc.cook.repository.info;

import com.mbc.cook.entity.info.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InfoRepository extends JpaRepository<CategoryEntity,Long> {
    @Override
    List<CategoryEntity> findAll();
}
