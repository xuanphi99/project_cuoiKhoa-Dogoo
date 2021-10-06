package com.speedhome.poc.service.entity;

import org.hibernate.search.annotations.Indexed;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Indexed
@Entity
@Table(name = "OWNER")
public class OwnerEnity {
    @Id
    @Column(name = "OWNER_ID", nullable = false)
    private String idOwner;

    @Column(name = "FULLNAME", nullable = false)
    private String fullName;

    @Column(name = "ADDRESS", nullable = false)
    private String address;

    @Column(name = "AGE", nullable = false)
    private int age;

    public String getIdOwner() {
        return idOwner;
    }

    public void setIdOwner(String idOwner) {
        this.idOwner = idOwner;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
