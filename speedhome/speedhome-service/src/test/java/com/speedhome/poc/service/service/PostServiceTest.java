package com.speedhome.poc.service.service;


import com.speedhome.poc.service.entity.PostEntity;
import com.speedhome.poc.service.mapper.PostMapper;
import com.speedhome.poc.service.mock.PostData;
import com.speedhome.poc.service.repository.PostRepository;
import com.speedhome.poc.service.validator.PostValidator;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

import java.util.List;

import static org.junit.Assert.assertEquals;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class PostServiceTest  {


    @InjectMocks
    PostService postService;
    List<PostEntity> postList;

    @Mock
    PostRepository postRepository;

    @Mock
    PostMapper postMapper;

    @Mock
    PostValidator postValidator;

    @Mock
    PostSearch postSearch;

    @BeforeEach
    void setUp() {
        postService = new PostService(postRepository,postMapper,postValidator,postSearch);
    }


    @Test
    public  void getAllPost(){
        postService.getAllPost(0,5);
    }

    @Test
    public void createPost(){
        postService.createPost(PostData.mockOwnerReq());
    }

    @Test
    public void getDetailPost() {
        postService.getDetailPost(PostData.idPost);
    }

    @Test
    public  void  removeItemPost(){
        postService.removeItemPost(PostData.idPost);
    }



}