package com.example.radioadsapp.model;

import jakarta.persistence.*;


@Entity
public class Role extends BaseEntity{

    @Column(unique = true)
    private String name;

    public Role() {

    }

    public Role(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}