package com.speedhome.poc.service.validator;

import com.speedhome.poc.api.model.ContactRequest;
import com.speedhome.poc.service.constant.Constants;
import com.speedhome.poc.service.exception.BadRequestException;
import com.speedhome.poc.service.repository.ContactRepository;
import com.speedhome.poc.service.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ContactValidator {

    private final ContactRepository repository;

    @Autowired
    public ContactValidator(ContactRepository repository) {
        this.repository = repository;
    }

    public void validateContactExist(String id) {
        if (repository.existsById(id)) {
            throw new BadRequestException(Constants.ContactMSG.USER_NAME_EMPTY);
        }
    }

    public void validateFiledContactEmpty(ContactRequest contactRequest){
        if (!CommonUtil.notNullOrEmpty(contactRequest.getMessage()))
            throw new BadRequestException(Constants.ContactMSG.MESSAGE_EMPTY);
        if (!CommonUtil.notNullOrEmpty(contactRequest.getUserName()))
            throw new BadRequestException(Constants.ContactMSG.USER_NAME_EMPTY);

        if (!contactRequest.getEmail().matches(Constants.ContactMSG.REGEX_EMAIL)
                    || !CommonUtil.notNullOrEmpty(contactRequest.getEmail()))
        {
            throw new BadRequestException(Constants.ContactMSG.EMAIL_FORMAT);
        }

    }



    }
