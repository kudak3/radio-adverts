package com.example.radioadsapp.model;

import com.example.radioadsapp.utils.CustomDateDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "notification")
public class NotificationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;

    @Temporal(TemporalType.DATE)
    @JsonDeserialize(using = CustomDateDeserializer.class)
    private Date created;

    private String affectedTable;

    @Column(columnDefinition = "boolean default false")
    private boolean viewed;

    @Column(columnDefinition = "boolean default false")
    private boolean web;

    public NotificationEntity() {

    }

    public boolean isWeb() {
        return web;
    }

    public void setWeb(boolean web) {
        this.web = web;
    }

    public NotificationEntity(String message, Date created, String affectedTable, boolean viewed, boolean web) {
        this.message = message;
        this.created = created;
        this.affectedTable = affectedTable;
        this.viewed = viewed;
        this.web = web;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getAffectedTable() {
        return affectedTable;
    }

    public void setAffectedTable(String affectedTable) {
        this.affectedTable = affectedTable;
    }

    public boolean isViewed() {
        return viewed;
    }

    public void setViewed(boolean viewed) {
        this.viewed = viewed;
    }
}
