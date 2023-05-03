package com.example.radioadsapp.model;

import com.example.radioadsapp.utils.CustomDateDeserializer;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Entity
@Table(name = "notification")
public class NotificationEntity extends BaseEntity {

    private String message;

    @Column(columnDefinition = "boolean default false")
    private boolean viewed;


    @ManyToOne
    private User user;

    public NotificationEntity() {

    }


    public NotificationEntity(String message, boolean viewed) {
        this.message = message;
        this.viewed = viewed;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public boolean isViewed() {
        return viewed;
    }

    public void setViewed(boolean viewed) {
        this.viewed = viewed;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
