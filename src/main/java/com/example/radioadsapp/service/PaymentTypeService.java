package com.example.radioadsapp.service;

import com.example.radioadsapp.model.PaymentType;

import java.util.List;

public interface PaymentTypeService extends IService<PaymentType,Long>{
    List<PaymentType> getAll();
}
