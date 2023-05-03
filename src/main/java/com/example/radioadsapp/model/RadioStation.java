package com.example.radioadsapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
public class RadioStation extends BaseEntity implements Serializable {

    @Column(unique = true)
    private String name;
    private String url;
    private String frequency;

    private String imageName;

    @JsonBackReference(value = "payment-radio-station")
    @OneToMany(mappedBy = "radioStation")
    private List<Payment> payments = new ArrayList<>();

    @JsonBackReference(value = "advert-radio-station")
    @OneToMany(mappedBy = "radioStation")
    private List<Advert> adverts = new ArrayList<>();

    @JsonBackReference(value = "program-radio-station")
    @OneToMany(mappedBy = "radioStation")
    private List<Program> programs = new ArrayList<>();

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public List<Advert> getAdverts() {
        return adverts;
    }

    public void setAdverts(List<Advert> adverts) {
        this.adverts = adverts;
    }

    public List<Program> getPrograms() {
        return programs;
    }

    public void setPrograms(List<Program> programs) {
        this.programs = programs;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    @Transient
    public String getImagePath() {
        if (imageName == null || id == null) return null;
//        return "/user-photos/stations/" + id + "/" + imageName;
        return "/img/" + id + "/" + imageName;
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