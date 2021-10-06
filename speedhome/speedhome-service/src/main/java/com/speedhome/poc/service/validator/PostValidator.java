package com.speedhome.poc.service.validator;

import com.speedhome.poc.api.model.Post;
import com.speedhome.poc.service.constant.Constants;
import com.speedhome.poc.service.exception.BadRequestException;
import com.speedhome.poc.service.exception.EntityNotFoundException;
import com.speedhome.poc.service.repository.PostRepository;
import com.speedhome.poc.service.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


@Component
public class PostValidator {



    private final PostRepository postRepository;

    @Autowired
    public PostValidator(PostRepository postRepository) {
        this.postRepository = postRepository;
    }


    public void validatePostExist(String id) {
        if (postRepository.existsById(id)) {
            return;
        }

        throw new EntityNotFoundException(Constants.ConstantPost.POST_DOES_NOT_EXIST);
    }


    public void validateFieldIsNull( Post post, Boolean flag) {

        if (Boolean.TRUE.equals(flag))
        {
            // create - post
            CommonUtil.isNullOrEmpty(post.getImgName(),Constants.ConstantPost.ERROR_REQUEST);
            CommonUtil.isNullOrEmpty(post.getImgURL(),Constants.ConstantPost.ERROR_REQUEST);
        }

        CommonUtil.isNullOrEmpty(post.getCategoryId(), Constants.ConstantPost.ERROR_REQUEST);
        CommonUtil.isNullOrEmpty(post.getContent(),Constants.ConstantPost.ERROR_REQUEST);
        CommonUtil.isNullOrEmpty(post.getDescription(),Constants.ConstantPost.ERROR_REQUEST);
        CommonUtil.isNullOrEmpty(post.getTitle(), Constants.ConstantPost.ERROR_REQUEST);
        CommonUtil.isNullOrEmpty(post.getCreateBy(),Constants.ConstantPost.ERROR_REQUEST);


    }

    public void validateFile(MultipartFile file){
        CommonUtil.isNullOrEmpty(file, Constants.ConstantPost.FILE_IS_NULL) ;
        if (file.getSize() > Constants.FileInfo.MAX_FILE_SIZE) {
            throw new BadRequestException(Constants.FileInfo.MAX_FILE_SIZE_MSG);
        }
        if (!Constants.FileInfo.format.contains( getExtentionFile(file.getOriginalFilename())))
        {
            throw new BadRequestException(Constants.FileInfo.ERROR_FORMAT_MSG);
        }

    }

    private String getIndexFormatFile(String s) {
        if (s != null) {
            return s.substring(s.lastIndexOf('/') + 1);
        }
        return "";
    }

    private String getExtentionFile(String path){
        return StringUtils.getFilenameExtension(getIndexFormatFile(path));
    }


    public void validateSearch(String search) {
        if (search.length() >= Constants.ConstantPost.MIN_LENGTH && search.length() <= Constants.ConstantPost.MAX_LENGTH) {
            return;
        }

        throw new BadRequestException(Constants.ConstantPost.TERM_INPUT_INVALID_MSG);
    }
}
