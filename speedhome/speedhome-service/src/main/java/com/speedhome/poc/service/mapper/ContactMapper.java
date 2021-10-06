package com.speedhome.poc.service.mapper;

import com.speedhome.poc.api.model.Contact;
import com.speedhome.poc.api.model.ContactRequest;
import com.speedhome.poc.service.entity.ContactEntity;
import com.speedhome.poc.service.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ContactMapper {

    private final ContactRepository contactRepository;

    @Autowired
    public ContactMapper(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public Contact mapContactFromContactEnity(ContactEntity from) {
        Contact to = new Contact();
        to.setIdContact(from.getIdContact());
        to.setEmail(from.getEmail());
        to.setMessage(from.getMessage());
        to.setUserName(from.getUserName());
        return to;
    }

    public ContactEntity mapContactEnityFromContactRed(ContactRequest from) {
        ContactEntity to = new ContactEntity();
        to.setIdContact(UUID.randomUUID().toString());
        to.setEmail(from.getEmail());
        to.setMessage(from.getMessage());
        to.setUserName(from.getUserName());
        return to;
    }
}
