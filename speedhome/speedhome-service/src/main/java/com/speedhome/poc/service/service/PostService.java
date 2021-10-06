package com.speedhome.poc.service.service;

import com.speedhome.poc.api.model.Paging;
import com.speedhome.poc.api.model.Post;
import com.speedhome.poc.api.model.PostResponse;
import com.speedhome.poc.service.entity.PostEntity;
import com.speedhome.poc.service.mapper.PostMapper;
import com.speedhome.poc.service.repository.PostRepository;
import com.speedhome.poc.service.validator.PostValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;


@Service
public class PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final PostValidator validator;
    private final PostSearch postSearch;

    @Autowired
    public PostService(PostRepository postRepository, PostMapper postMapper, PostValidator validator, PostSearch postSearch) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
        this.validator = validator;
        this.postSearch = postSearch;
    }

    public PostResponse getAllPost(Integer currentPage, Integer pageSize) {
        Pageable pageable = PageRequest.of(currentPage, pageSize, Sort.by("createDate").descending());
        Page<PostEntity> postEnity  = postRepository.findAll(pageable) ;


        Paging paging = new Paging();
        paging.setCurrentPage(currentPage);
        paging.setPageSize(pageSize);

        if(postEnity != null && pageSize > 0 ) {
            int totalItem = Integer.parseInt(String.valueOf(postEnity.getTotalElements()));
            paging.setTotalElement(totalItem);
            if (totalItem % pageSize == 0)
             paging.setTotalPage(totalItem / pageSize);
            else
                paging.setTotalPage(totalItem / pageSize + 1);
        }
        PostResponse postResponse = new PostResponse();
        postResponse.setData( postMapper.mapPostsFromPagePostEnity(postEnity));
        postResponse.setPaging(paging);
        return postResponse;
    }


    public Post createPost(Post postRequest) {
        postRequest.setIdPost(UUID.randomUUID().toString());
        PostEntity postEntity = postRepository.save(postMapper.mapPostEntityFromPost(postRequest));
        return postMapper.mapPostDtoFromPostEnity(postRepository.save(postEntity));
    }

    public Post getDetailPost(String idPost) {
        validator.validatePostExist(idPost);
        Optional<PostEntity> entity = postRepository.findById(idPost);
        return postMapper.mapPostDtoFromPostEnity(entity.orElse(null));
    }

    public void removeItemPost(String idPost) {
        postRepository.deleteById(idPost);

    }

    public Post updatePost(Post postRequest, String idPost) {
        postRequest.setIdPost(idPost);
        Map<String, String> mapCategory = postMapper.findAllCategory();
        for (Map.Entry<String, String> entry : mapCategory.entrySet()) {
            if (entry.getValue().equals(postRequest.getCategoryId())) {
                postRequest.setCategoryId(entry.getKey());
            }
        }
        PostEntity postEntity = postRepository.save(postMapper.mapPostEntityFromPost(postRequest));
        return postMapper.mapPostDtoFromPostEnity(postRepository.save(postEntity));

    }

    // FULL TEXT SEARCH
    public PostResponse search(String search,Integer currentPage, Integer pageSize) {
       List<PostEntity> entityList = postSearch.searchPost(search);
        Pageable page = new PageRequest(entityList.isEmpty() ? 1 : entityList.size(), 1);
        int end = Math.min((page.getPageSize()), entityList.size());
        Page <PostEntity> pageToReturn = new PageImpl<>(entityList.subList(0,end),page,entityList.size());

        Page<PostEntity> postEnity  = pageToReturn ;

        Paging paging = new Paging();
        paging.setCurrentPage(currentPage);
        paging.setPageSize(pageSize);
        if(postEnity != null && pageSize > 0 ) {
            int totalItem = Integer.parseInt(String.valueOf(postEnity.getTotalElements()));
            paging.setTotalElement(totalItem);
            if (totalItem % pageSize == 0)
                paging.setTotalPage(totalItem / pageSize);
            else
                paging.setTotalPage(totalItem / pageSize + 1);
        }
        PostResponse postResponse = new PostResponse();
        postResponse.setData( postMapper.mapPostsFromPagePostEnity(postEnity));
        postResponse.setPaging(paging);

        return postResponse;
    }
}
