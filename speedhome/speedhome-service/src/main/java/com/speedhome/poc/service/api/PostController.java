package com.speedhome.poc.service.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.speedhome.poc.api.PostsApi;
import com.speedhome.poc.api.model.Post;
import com.speedhome.poc.api.model.PostResponse;
import com.speedhome.poc.service.exception.BadRequestException;
import com.speedhome.poc.service.search.HibernateSearchUtil;
import com.speedhome.poc.service.search.IndexingService;
import com.speedhome.poc.service.service.AmazonClientImpl;
import com.speedhome.poc.service.service.PostService;
import com.speedhome.poc.service.validator.PostValidator;
import com.speedhome.poc.service.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@CrossOrigin
@RestController
@RequestMapping("/speedhome/backend/v1")
public class PostController implements PostsApi {

    private final PostService postService;
    private final PostValidator validator;
    private final AmazonClientImpl amazonClient;
    private final IndexingService indexingServicel;

    @Autowired
    public PostController(PostService postService, PostValidator validator, AmazonClientImpl amazonClient, IndexingService indexingServicel) {
        this.postService = postService;
        this.validator = validator;
        this.amazonClient = amazonClient;
        this.indexingServicel = indexingServicel;
    }

    @Override
    public ResponseEntity<PostResponse> getAllPost( @RequestParam(value = "currentPage", required = true, defaultValue="0") Integer currentPage,
                                                      @RequestParam(value = "pageSize", required = false, defaultValue="5") Integer pageSize,
                                                    @RequestParam(value = "search", required = false) String search) {
          if ( CommonUtil.notNullOrEmpty(search) ){

              search = HibernateSearchUtil.decodeUrl(search);
              validator.validateSearch(search);
              return new ResponseEntity<>(postService.search(search,currentPage,pageSize), HttpStatus.OK);
          }
            return new ResponseEntity<>(postService.getAllPost(currentPage,pageSize), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Post>  addPost( @RequestPart("file") MultipartFile file, @RequestParam(value="jsonData", required=true)  String jsonData)  {
        ObjectMapper objectMapper = new ObjectMapper();
        try {

        Post postRequest = objectMapper.readValue(jsonData,Post.class);
        validator.validateFile(file);
        String fileInfo = amazonClient.uploadFile(file);
        postRequest.setImgName(fileInfo.split("\\;")[1]);
        postRequest.setImgURL(fileInfo.split("\\;")[0]);
        postRequest.setCreateDate(CommonUtil.convertStringToLocalDate(LocalDate.now().toString(), "yyyy-MM-dd"));
        validator.validateFieldIsNull(postRequest,true);
        Post postDto = postService.createPost(postRequest);
        return new  ResponseEntity<>(postDto,HttpStatus.CREATED);
        }
        catch (Exception e){
            throw new BadRequestException(e.getMessage());
        }
    }

    @Override
    public  ResponseEntity<Post> getDetailPost( @PathVariable("idPost") String idPost) {
       return new ResponseEntity<>(postService.getDetailPost(idPost),HttpStatus.OK);
    }

    @Override
    public  ResponseEntity<Void> removeItemPost(@PathVariable("idPost") String idPost){
        postService.removeItemPost(idPost);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Post> updatePostItem(
        @PathVariable("idPost") String idPost,
        @RequestParam(value="jsonData", required=true)  String jsonData,
        @RequestPart(value = "file" , required = false) MultipartFile file ) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Post postRequest = objectMapper.readValue(jsonData,Post.class);
            if (!CommonUtil.nullOrEmpty(file) ) {
                validator.validateFile(file);
                String fileInfo = amazonClient.uploadFile(file);
                postRequest.setImgName(fileInfo.split("\\;")[1]);
                postRequest.setImgURL(fileInfo.split("\\;")[0]);
            }
            postRequest.setModifyDate(LocalDate.now());

            validator.validateFieldIsNull(postRequest,false);
            Post postDto = postService.updatePost(postRequest,idPost);
            return new  ResponseEntity<>(postDto,HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            throw new BadRequestException(e.getMessage());
        }

}



}
