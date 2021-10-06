package com.speedhome.poc.service.repository;


import com.speedhome.poc.service.entity.OwnerEnity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends JpaRepository<OwnerEnity , String> {
    boolean existsById(String id);
}
