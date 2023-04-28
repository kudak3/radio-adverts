package com.example.radioadsapp.service;

import com.example.radioadsapp.model.Payment;
import com.example.radioadsapp.model.PaymentType;

import java.util.List;

public interface PaymentsService extends IService<Payment,Long>{
    List<Payment> getAll();
}
