package com.mbc.cook.service.random;

import com.mbc.cook.entity.recipe.RecipeEntity;
import com.mbc.cook.repository.random.RandomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RandomServiceIMP implements RandomService {
    @Autowired
    RandomRepository randomRepository;

    @Override
    public List<RecipeEntity> getFood() {
        return randomRepository.findAll();
    }
}
