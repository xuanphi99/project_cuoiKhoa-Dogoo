package com.speedhome.poc.service.mapper;

import com.speedhome.poc.api.model.Post;
import com.speedhome.poc.api.model.Posts;
import com.speedhome.poc.service.entity.CategoryEntity;
import com.speedhome.poc.service.entity.PostEntity;
import com.speedhome.poc.service.repository.CategoryRepository;
import com.speedhome.poc.service.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class PostMapper {
    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public PostMapper(PostRepository postRepository, CategoryRepository categoryRepository) {
        this.postRepository = postRepository;
        this.categoryRepository = categoryRepository;
    }




    public Post mapPostDtoFromPostEnity(PostEntity from){
        Post to = new Post();
        Map<String, String> mapCategory = findAllCategory();
        to.setIdPost(from.getIdPost());
        to.setCategoryId(from.getCategoryId());
        to.setImgName(from.getImgName());
        to.setImgURL(from.getImgURL());
        to.setTitle(from.getTitle());
        to.setCreateDate(from.getCreateDate());
        to.setCreateBy(from.getCreateBy());
        to.setModifyDate(from.getModifyDate());
        to.setModifyBy(from.getModifyBy());
        to.setDescription(from.getDescription());
        to.setContent(from.getContent());
        to.setCategoryId(mapCategory.get(from.getCategoryId()));
        return to;
    }

    public Posts mapPostsFromPagePostEnity(Page<PostEntity> from) {
        return  from.stream().map(this::mapPostDtoFromPostEnity)
                .collect(Collectors.toCollection(Posts::new));
    }

    public PostEntity mapPostEntityFromPost(Post from) {
        PostEntity to = new PostEntity();
        to.setIdPost(from.getIdPost());
        to.setCategoryId(from.getCategoryId());
        to.setImgName(from.getImgName());
        to.setImgURL(from.getImgURL());
        to.setTitle(from.getTitle());
        to.setCreateDate(from.getCreateDate());
        to.setCreateBy(from.getCreateBy());
        to.setModifyDate(from.getModifyDate());
        to.setModifyBy(from.getModifyBy());
        to.setDescription(from.getDescription());
        to.setContent(from.getContent());
        to.setCategoryId(from.getCategoryId());
        return to;
    }


    public Map<String, String> findAllCategory() {
        List<CategoryEntity> categoryEnityList = categoryRepository.findAll();

        Map<String, String> mapCategory = new HashMap<>();
        if (!CollectionUtils.isEmpty(categoryEnityList)){
            categoryEnityList.forEach(item ->  {
                if (!mapCategory.containsKey(item.getIdCategory())){
                    mapCategory.put(item.getIdCategory(),item.getName());
                }
            } );
        }
        return mapCategory;
    }
}
