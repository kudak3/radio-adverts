package com.example.radioadsapp.dto;


import java.util.List;


public class SubscriptionRequestDto {

    public SubscriptionRequestDto(String topicName, List<String> tokens) {
        this.topicName = topicName;
        this.tokens = tokens;
    }

    String topicName;
    List<String> tokens;

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public List<String> getTokens() {
        return tokens;
    }

    public void setTokens(List<String> tokens) {
        this.tokens = tokens;
    }
}