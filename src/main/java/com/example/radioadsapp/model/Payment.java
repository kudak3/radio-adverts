package com.example.radioadsapp.model;


import com.example.radioadsapp.utils.CustomDateDeserializer;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;



@Entity
public class Payment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference(value="payment-client")
    @ManyToOne
    private Client client;

    @JsonBackReference(value="payment-advert")
    @ManyToOne
    private Advert advert;

    @JsonBackReference(value="payment-radio")
    @ManyToOne
    private RadioStation radioStation;

    private String accountNumber;
    private String advertNumber;


    @OneToOne
    private PaymentType paymentType;
    private Long amount;
    private String description;

    @Temporal(TemporalType.DATE)
    @JsonDeserialize(using = CustomDateDeserializer.class)
    private Date date;


    @Column(columnDefinition = "boolean default true")
    private boolean newEntry;

    public boolean isNewEntry() {
        return newEntry;
    }

    public void setNewEntry(boolean newEntry) {
        this.newEntry = newEntry;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAdvertNumber() {
        return advertNumber;
    }

    public void setAdvertNumber(String advertNumber) {
        this.advertNumber = advertNumber;
    }

    public Advert getAdvert() {
        return advert;
    }

    public void setAdvert(Advert advert) {
        this.advert = advert;
    }

    public RadioStation getRadioStation() {
        return radioStation;
    }

    public void setRadioStation(RadioStation radioStation) {
        this.radioStation = radioStation;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", client=" + client.getFirstName() +
                ", accountNumber='" + accountNumber + '\'' +
                ", advertNumber='" + advertNumber + '\'' +
                ", paymentType=" + paymentType +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", date=" + date +
                '}';
    }
}
