package com.example.radioadsapp.service;

import com.example.radioadsapp.model.Advert;

import java.util.List;

public interface AdvertService extends IService<Advert,Long>{
    List <Advert> getAll();
}
