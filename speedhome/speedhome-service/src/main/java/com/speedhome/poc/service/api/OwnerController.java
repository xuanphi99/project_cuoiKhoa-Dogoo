package com.speedhome.poc.service.api;

import com.speedhome.poc.api.OwnerApi;
import com.speedhome.poc.api.model.Owner;
import com.speedhome.poc.api.model.OwnerRequest;
import com.speedhome.poc.api.model.Owners;
import com.speedhome.poc.service.service.OwnerService;
import com.speedhome.poc.service.validator.OwerValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/speedhome/backend/v1")
@CrossOrigin
public class OwnerController implements OwnerApi {

    private final OwnerService service;
    private final OwerValidator validator;

    @Autowired
    public OwnerController(OwnerService service, OwerValidator validator) {
        this.service = service;
        this.validator = validator;
    }

    @Override
    public ResponseEntity<Owner> addOwner( @RequestBody OwnerRequest request) {
        validator.validateAddOwner(request);
        Owner owner = service.createOwner(request);
        return new ResponseEntity<>(owner, HttpStatus.OK);

    }

    @Override
    public  ResponseEntity<Owner> updateOwner( @PathVariable("idOwner") String idOwner,
                                                @RequestBody OwnerRequest request) {
        validator.validateUpdateOwner(idOwner,request);
        Owner owner = service.updateOwner(idOwner,request);
        return new ResponseEntity<>(owner, HttpStatus.OK);
        }
    @Override
    public ResponseEntity<Void> removeOwner(@PathVariable("idOwner") String idOwner){
        validator.validateremoveOwner(idOwner);
        service.removeOwner(idOwner);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Owners> getAllOwner(){
        return new ResponseEntity<>(service.getAllOwner(), HttpStatus.OK);
    }


}




