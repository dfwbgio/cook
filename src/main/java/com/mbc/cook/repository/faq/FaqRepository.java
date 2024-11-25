package com.mbc.cook.repository.faq;

import com.mbc.cook.entity.faq.FaqEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FaqRepository extends JpaRepository<FaqEntity,Long> {
}
