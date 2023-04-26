package com.example.radioadsapp.model;

import com.example.radioadsapp.utils.AdvertType;
import com.example.radioadsapp.utils.CustomDateDeserializer;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Advert implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    //attributes
    private Long id;

    private Long resourceId;
    private String title;
    private String description;
    @Enumerated
    private AdvertType advertType;

    private String url;
    @JsonBackReference(value="advert-radio-station")
    @ManyToOne
    private RadioStation radioStation;

    @JsonBackReference(value="advert-client")
    @ManyToOne
    private Client client;

    @Column( nullable = false, updatable = false)
//    @Temporal(TemporalType.TIMESTAMP)
//    @JsonDeserialize(using = CustomDateDeserializer.class)
    private LocalDateTime start;


    @Column( nullable = false)
//    @Temporal(TemporalType.TIME)
//    @JsonDeserialize(using = CustomDateDeserializer.class)
    private LocalDateTime end;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AdvertType getAdvertType() {
        return advertType;
    }

    public void setAdvertType(AdvertType advertType) {
        this.advertType = advertType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public RadioStation getRadioStation() {
        return radioStation;
    }

    public void setRadioStation(RadioStation radioStation) {
        this.radioStation = radioStation;
    }

    public Client getClient() {
        return client;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "Advert{" +
                "id=" + id +
                ", name='" + title + '\'' +
                ", description='" + description + '\'' +
                ", radio-station='" + radioStation + '\'' +
                ", start='" + start + '\'' +
                ", end='" + end + '\'' +
                '}';
    }
}
