package com.example.radioadsapp.service.impl;

import com.example.radioadsapp.model.Advert;
import com.example.radioadsapp.repository.AdvertRepository;
import com.example.radioadsapp.service.AdvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdvertServiceImpl implements AdvertService {
    private final AdvertRepository advertRepository;

    public AdvertServiceImpl(AdvertRepository advertRepository) {
        this.advertRepository = advertRepository;
    }

    @Override
    public Advert save(Advert advert) {
        return advertRepository.save(advert);
    }

    @Override
    public void delete(Long id) {
    advertRepository.deleteById(id);
    }

    @Override
    public List<Advert> getAll() {
        return advertRepository.findAll();
    }
}
