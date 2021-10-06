package com.speedhome.poc.service.mapper;

import com.speedhome.poc.api.model.Categories;
import com.speedhome.poc.api.model.Category;
import com.speedhome.poc.service.entity.CategoryEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryMapper {



    public Categories mapCategoriesFromListCategoryEnity(List<CategoryEntity> entityList) {
        Categories to = new Categories();
        for ( CategoryEntity from : entityList) {
            Category category = new Category();
            category.setIdCategory(from.getIdCategory());
            category.setName(from.getName());
            to.add(category);
        }
        return to;
    }
}
