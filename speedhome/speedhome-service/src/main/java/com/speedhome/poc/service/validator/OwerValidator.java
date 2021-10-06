package com.speedhome.poc.service.validator;

import com.speedhome.poc.api.model.OwnerRequest;
import com.speedhome.poc.service.exception.EntityNotFoundException;
import com.speedhome.poc.service.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OwerValidator {

    private static final String PROPERTY_DOES_NOT_EXIST = "Property does not exist";

    private static final String NAME_REQUEST = "Property name is requested";

    private final OwnerRepository repository;

    @Autowired
    public OwerValidator(OwnerRepository repository) {
        this.repository = repository;
    }

    public void validateAddOwner(OwnerRequest request) {
        checkRequiredField(request.getFullName(),NAME_REQUEST);
    }

    private void validatePropertyExist(String id) {
        if (repository.existsById(id)) {return;}

        throw new EntityNotFoundException(PROPERTY_DOES_NOT_EXIST);
    }


    private void checkRequiredField(String value, String errorMsg) {

        if (null == value || value.trim().isEmpty()) {

        }
    }


    public void validateUpdateOwner(String idOwner, OwnerRequest request) {
        validatePropertyExist(idOwner);
        checkRequiredField(request.getFullName(),NAME_REQUEST);
    }

    public void validateremoveOwner(String idOwner) {
        validatePropertyExist(idOwner);
    }
}
