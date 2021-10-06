package com.speedhome.poc.service.service;

import com.speedhome.poc.api.model.Owner;
import com.speedhome.poc.api.model.OwnerRequest;
import com.speedhome.poc.api.model.Owners;
import com.speedhome.poc.service.entity.OwnerEnity;
import com.speedhome.poc.service.mapper.OwnerMapper;
import com.speedhome.poc.service.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnerService {

    private final OwnerRepository ownerRepository;

    private  final OwnerMapper ownerMapper ;

    @Autowired
    public OwnerService(OwnerRepository ownerRepository, OwnerMapper ownerMapper) {
        this.ownerRepository = ownerRepository;
        this.ownerMapper = ownerMapper;
    }

    public Owner createOwner(OwnerRequest request) {
        OwnerEnity ownerEnity = ownerMapper.mapOwnerEnityFromOwnerReq(request);
        return  ownerMapper.mapOwnerFromOwnerEnity(ownerRepository.save(ownerEnity));
    }

    public Owner updateOwner(String idOwner, OwnerRequest request) {
        OwnerEnity ownerEnity = ownerMapper.mapOwnerEnityFromOwnerReq(idOwner,request);
        return ownerMapper.mapOwnerFromOwnerEnity(ownerRepository.save(ownerEnity));
    }

    public void removeOwner(String idOwner) {
        ownerRepository.deleteById(idOwner);
    }


    public Owners getAllOwner() {
        List<OwnerEnity> enityList = ownerRepository.findAll();
        return ownerMapper.mapOwnersFromOwnerEntity(enityList);
    }
}
