package com.example.radioadsapp.model;

import jakarta.persistence.*;


@Entity
public class RadioStation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;

    public RadioStation() {

    }

    public RadioStation(String name) {
        super();
        this.name = name;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "RadioStation{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}