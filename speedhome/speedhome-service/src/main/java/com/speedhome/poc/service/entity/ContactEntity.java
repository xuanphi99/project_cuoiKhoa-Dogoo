package com.speedhome.poc.service.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CONTACT")
public class ContactEntity {
    @Id
    @Column(name = "ID_CONTACT", nullable = false)
    private String idContact;

    @Column(name = "USER_NAME", nullable = false)
    private String userName;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "MESSAGE", nullable = false)
    private String message;


    public ContactEntity(String idContact, String userName, String email, String message) {
        this.idContact = idContact;
        this.userName = userName;
        this.email = email;
        this.message = message;
    }

    public ContactEntity() {
    }

    public String getIdContact() {
        return idContact;
    }

    public void setIdContact(String idContact) {
        this.idContact = idContact;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
