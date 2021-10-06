package com.speedhome.poc.service.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CATEGORY")
public class CategoryEntity {
    @Id
    @Column(name = "ID_CATEGORY", nullable = false)
    private String idCategory;

    @Column(name = "NAME", nullable = false)
    private String name;

    public CategoryEntity(String idCategory, String name) {
        this.idCategory = idCategory;
        this.name = name;
    }

    public CategoryEntity() {
    }

    public String getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(String idCategory) {
        this.idCategory = idCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
