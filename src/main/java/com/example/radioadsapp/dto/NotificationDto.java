package com.example.radioadsapp.dto;


public class NotificationDto {

    private String target;
    private String title;
    private String body;

    public NotificationDto() {
    }

    public NotificationDto(String target, String title, String body) {
        this.target = target;
        this.title = title;
        this.body = body;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}