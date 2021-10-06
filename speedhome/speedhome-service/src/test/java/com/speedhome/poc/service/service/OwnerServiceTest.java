package com.speedhome.poc.service.service;

import com.speedhome.poc.api.model.Owner;
import com.speedhome.poc.api.model.OwnerRequest;
import com.speedhome.poc.service.mapper.OwnerMapper;
import com.speedhome.poc.service.mock.OwnerData;
import com.speedhome.poc.service.repository.OwnerRepository;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@RunWith(MockitoJUnitRunner.class)
public class OwnerServiceTest extends TestCase {
    @InjectMocks
    OwnerService ownerService;

    @Mock
    OwnerRepository ownerRepository;

    @Mock
    OwnerMapper ownerMapper ;

    @Test
    public void createOwner(){
       ownerService.createOwner(OwnerData.mockOwnerReq());
    }

    @Test
    public void updateOwner() {
        ownerService.updateOwner(OwnerData.idOwner,OwnerData.mockOwnerReq());
    }

    @Test
    public void removeOwner(){
        ownerService.removeOwner(OwnerData.idOwner);
    }
    @Test
    public void getAllOwner(){
        ownerService.getAllOwner();

    }
}