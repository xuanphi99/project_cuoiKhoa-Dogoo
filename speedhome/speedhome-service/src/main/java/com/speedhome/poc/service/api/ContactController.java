package com.speedhome.poc.service.api;

import com.speedhome.poc.api.ContactsApi;
import com.speedhome.poc.api.model.Contact;
import com.speedhome.poc.api.model.ContactRequest;
import com.speedhome.poc.service.service.ContactService;
import com.speedhome.poc.service.validator.ContactValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/speedhome/backend/v1")
@CrossOrigin
public class ContactController implements ContactsApi {
    private final ContactValidator validator;
    private final ContactService contactService;

    @Autowired
    public ContactController(ContactValidator validator, ContactService contactService) {
        this.validator = validator;
        this.contactService = contactService;
    }

    @Override
    public ResponseEntity<Contact> addContact(
            @RequestBody ContactRequest request) {
        return new ResponseEntity<>(contactService.createContact(request), HttpStatus.CREATED);

    }

}
