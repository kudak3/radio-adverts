package com.example.radioadsapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
public class RadioStation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;

    @JsonBackReference(value = "payment-radio-station")
    @OneToMany(mappedBy = "radioStation")
    private List<Payment> payments = new ArrayList<>();

    @JsonBackReference(value = "advert-radio-station")
    @OneToMany(mappedBy = "radioStation")
    private List<Advert> adverts = new ArrayList<>();


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

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    @Override
    public String toString() {
        return "RadioStation{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", payments='" + payments + '\'' +
                '}';
    }
}