package com.speedhome.poc.service.mock;


import com.speedhome.poc.api.model.Post;
import com.speedhome.poc.service.entity.PostEntity;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.Optional;

public class PostData {

    public static final  String  idPost = "C34543df-sa23-44f2-543d-6y454213d5f" ;
    public static final  String  imgURL = "https://s3.us-west-1.amazonaws.com/management-book/1632925835563-chess.jpg";
    public static final  String  imgName = "1632925835563-chess.jpg" ;
    public static final  String  title = "view its constituent classes" ;
    public static final  LocalDate  createDate = LocalDate.now() ;
    public static final  String  createBy = "phidx" ;
    public static final LocalDate modifyDate = LocalDate.now() ;
    public static final  String  modifyBy = "phidx" ;
    public static final  String  description = "view its constituent classes" ;
    public static final  String  content = "view its constituent classes" ;
    public static final  String categoryId = "012A-2030b-2300c" ;

    public static Post mockOwnerReq() {
        Post post = new Post();
        post.setIdPost(idPost);
        post.setImgURL(imgURL);
        post.setImgName(imgName);
        post.setTitle(title);
        post.setCreateDate(createDate);
        post.setCreateBy(createBy);
        post.modifyDate(modifyDate);
        post.modifyBy(modifyBy);
        post.setDescription(description);
        post.setContent(content);
        post.setCategoryId(categoryId);

        return post;
    }

    public static PostEntity mockPostEnity(){

        PostEntity post = new PostEntity();
        post.setIdPost(idPost);
        post.setImgURL(imgURL);
        post.setImgName(imgName);
        post.setTitle(title);
        post.setCreateDate(createDate);
        post.setCreateBy(createBy);
        post.setModifyDate(modifyDate);
        post.setModifyBy(modifyBy);
        post.setDescription(description);
        post.setContent(content);
        post.setCategoryId(categoryId);
        return post;
    }


}
