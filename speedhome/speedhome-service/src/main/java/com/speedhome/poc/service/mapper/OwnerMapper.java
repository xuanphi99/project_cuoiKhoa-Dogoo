package com.speedhome.poc.service.mapper;

import com.speedhome.poc.api.model.Owner;
import com.speedhome.poc.api.model.OwnerRequest;
import com.speedhome.poc.api.model.Owners;
import com.speedhome.poc.service.entity.OwnerEnity;
import com.speedhome.poc.service.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class OwnerMapper {

    private final OwnerRepository ownerRepository;

    @Autowired
    public OwnerMapper(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    public Owner mapOwnerFromOwnerEnity(OwnerEnity from) {
        Owner to = new Owner();
        to.setIdOwner(from.getIdOwner());
        to.setFullName(from.getFullName());
        to.setAddress(from.getAddress());
        to.setAge(from.getAge());
        return to;
    }

    public OwnerEnity mapOwnerEnityFromOwnerReq(OwnerRequest from ){
        OwnerEnity to = new OwnerEnity();
        to.setIdOwner(UUID.randomUUID().toString());
        to.setFullName(from.getFullName());
        to.setAddress(from.getAddress());
        to.setAge(from.getAge());
        return to;
    }
    public OwnerEnity mapOwnerEnityFromOwnerReq(String idOwner ,OwnerRequest from ){

        OwnerEnity to = ownerRepository.getOne(idOwner);
        to.setIdOwner(UUID.randomUUID().toString());
        to.setFullName(from.getFullName());
        to.setAddress(from.getAddress());
        to.setAge(from.getAge());
        return to;
    }

    public Owners mapOwnersFromOwnerEntity(List<OwnerEnity> enityList) {
        Owners owners = new Owners();
        for ( OwnerEnity ownerEnity : enityList) {
            owners.add(mapOwnerFromOwnerEnity(ownerEnity));
        }
        return owners;
    }
}
