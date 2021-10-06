package com.speedhome.poc.service.service;

import com.speedhome.poc.api.model.Contact;
import com.speedhome.poc.api.model.ContactRequest;
import com.speedhome.poc.service.entity.ContactEntity;
import com.speedhome.poc.service.mapper.ContactMapper;
import com.speedhome.poc.service.repository.ContactRepository;
import com.speedhome.poc.service.validator.ContactValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactService {

    private final ContactValidator validator;
    private final ContactRepository repository;
    private final ContactMapper contactMapper;

    @Autowired
    public ContactService(ContactValidator validator, ContactRepository repository, ContactMapper contactMapper) {
        this.validator = validator;
        this.repository = repository;
        this.contactMapper = contactMapper;
    }


    public Contact createContact(ContactRequest request) {
        validator.validateFiledContactEmpty(request);
        ContactEntity contactEntity = contactMapper.mapContactEnityFromContactRed(request);
        validator.validateContactExist(contactEntity.getIdContact());
        return contactMapper.mapContactFromContactEnity(repository.save(contactEntity));

    }
}
