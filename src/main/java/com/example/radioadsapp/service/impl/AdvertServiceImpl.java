package com.example.radioadsapp.service.impl;

import com.example.radioadsapp.model.Advert;
import com.example.radioadsapp.model.Client;
import com.example.radioadsapp.model.RadioStation;
import com.example.radioadsapp.repository.AdvertRepository;
import com.example.radioadsapp.service.AdvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Advert findByTitle(String title){

        if (title == null || title.isBlank()) {
            return null;
        }
        System.out.println("*******************");
        System.out.println(title);
        System.out.println("*******************");
        return advertRepository.findAdvertByTitle(title).orElse(null);
    }

    public Advert update(Advert updatedAdvert) {

        return advertRepository.findById(updatedAdvert.getId())
                .map(advert-> {
                    advert.setResourceId(updatedAdvert.getResourceId());
                    advert.setTitle(updatedAdvert.getTitle());
                    advert.setDescription(updatedAdvert.getDescription());
                    advert.setClient(updatedAdvert.getClient());
                    advert.setRadioStation(updatedAdvert.getRadioStation());
                    advert.setAdvertType(updatedAdvert.getAdvertType());
                    advert.setStart(updatedAdvert.getStart());
                    advert.setEnd(updatedAdvert.getEnd());
                    advert.setUrl(advert.getUrl());
                    return advertRepository.save(advert);
                })
                .orElse(null);

    }

    public Advert postAdvert(Advert postAdvert) {

        return advertRepository.findById(postAdvert.getId())
                .map(advert-> {
                    advert.setUrl(postAdvert.getUrl());
                    return advertRepository.save(advert);
                })
                .orElse(null);

    }

    public Advert getAdvert(Long id) {
        return advertRepository.findById(id).orElse(null);
    }
}
