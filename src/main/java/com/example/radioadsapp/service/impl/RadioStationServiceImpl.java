package com.example.radioadsapp.service.impl;

import com.example.radioadsapp.model.RadioStation;
import com.example.radioadsapp.model.RadioStation;
import com.example.radioadsapp.repository.RadioStationRepository;
import com.example.radioadsapp.service.RadioStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RadioStationServiceImpl implements RadioStationService {

    @Autowired
    private RadioStationRepository radioStationRepository;

    public List<RadioStation> getRadioStations(){
        return radioStationRepository.findAll();
    }



    @Override
    public RadioStation save(RadioStation radioStation) {
        return radioStationRepository.save(radioStation);
    }

    @Override
    public void delete(Long id){
        radioStationRepository.deleteById(id);
    }

    public void updateRadioStation(RadioStation radioStation){
        radioStationRepository.save(radioStation);
    }

    public RadioStation getRadioStation(Long id){
        RadioStation radioStation = radioStationRepository.findById(id).orElse(null);

        if (radioStation==null) {

            throw new RuntimeException("Cannot find Radio Station with id: " + id);

        }

        else return radioStation;
    }

}
