package com.speedhome.poc.service.repository;

import com.speedhome.poc.service.entity.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<ContactEntity, String> {
    boolean existsById(String id);
}
