package com.speedhome.poc.service.service;

import com.speedhome.poc.api.model.Categories;
import com.speedhome.poc.service.entity.CategoryEntity;
import com.speedhome.poc.service.mapper.CategoryMapper;
import com.speedhome.poc.service.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    public Categories getAllCategory() {
        List<CategoryEntity> entityList = categoryRepository.findAll();

        return categoryMapper.mapCategoriesFromListCategoryEnity(entityList) ;
    }
    
    
    
}
