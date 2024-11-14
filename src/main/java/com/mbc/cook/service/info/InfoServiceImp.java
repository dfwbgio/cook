package com.mbc.cook.service.info;

import com.mbc.cook.entity.info.CategoryEntity;
import com.mbc.cook.repository.info.InfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
}
