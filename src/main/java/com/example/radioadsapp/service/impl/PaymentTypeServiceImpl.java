package com.example.radioadsapp.service.impl;

import com.example.radioadsapp.model.PaymentType;
import com.example.radioadsapp.repository.PaymentTypeRepository;
import com.example.radioadsapp.service.PaymentTypeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentTypeServiceImpl implements PaymentTypeService {
    private final PaymentTypeRepository paymentTypeRepository;

    public PaymentTypeServiceImpl(PaymentTypeRepository paymentTypeRepository) {
        this.paymentTypeRepository = paymentTypeRepository;
    }


    @Override
    public PaymentType save(PaymentType paymentType) {
        return paymentTypeRepository.save(paymentType);
    }

    @Override
    public void delete(Long id){
        paymentTypeRepository.deleteById(id);
    }

    @Override
    public List<PaymentType> getAll(){
        return paymentTypeRepository.findAll();
    }



    public void updatePaymentType(PaymentType payment){
        paymentTypeRepository.save(payment);
    }

    public PaymentType getPaymentType(Long id) {
        return paymentTypeRepository.findById(id).orElse(null);
    }



}
