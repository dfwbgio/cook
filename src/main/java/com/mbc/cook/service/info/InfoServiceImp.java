package com.mbc.cook.service.info;

import com.mbc.cook.entity.info.CategoryEntity;
import com.mbc.cook.repository.info.InfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InfoServiceImp implements InfoService {
    @Autowired
    InfoRepository infoRepository;

    @Override
    public List<CategoryEntity> getCategory() {
        return infoRepository.findAll();
    }

    @Override
    public CategoryEntity getCategoryList(long num) {
        return infoRepository.findById(num).orElse(null);
    }

    @Override
    public void categorySave(CategoryEntity categoryEntity) {
        infoRepository.save(categoryEntity);
    }

    @Override
    public void categoryupdate(CategoryEntity categoryEntity) {
        infoRepository.save(categoryEntity);
    }

    @Override
    public void categorydelete(long num) {
        infoRepository.deleteById(num);
    }

    @Override
    public List<CategoryEntity> getSubCategoryList(String category) {
        return infoRepository.getSubCategoryList(category);
    }

    @Override
    public Page<CategoryEntity> categoryList(int page) {
        return infoRepository.findAll(PageRequest.of(page,10, Sort.by(Sort.Direction.DESC,"categorynum")));
    }
}
