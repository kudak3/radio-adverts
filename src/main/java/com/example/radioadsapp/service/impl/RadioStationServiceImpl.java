package com.example.radioadsapp.service.impl;

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

    public List<RadioStation> getRadioStations() {
        return radioStationRepository.findAll();
    }


    @Override
    public RadioStation save(RadioStation radioStation) {
        return radioStationRepository.save(radioStation);
    }

    @Override
    public void delete(Long id) {
        radioStationRepository.deleteById(id);
    }

    public void updateRadioStation(RadioStation radioStation) {
        radioStationRepository.save(radioStation);
    }

    public RadioStation getRadioStation(Long id) {
        return radioStationRepository.findById(id).orElse(null);
    }

    public RadioStation update(RadioStation updatedStation) {

        return radioStationRepository.findById(updatedStation.getId())
                .map(radioStation -> {
                    System.out.println("===========================********************");
                    System.out.println(radioStation);
                    radioStation.setName(updatedStation.getName());
                    radioStation.setFrequency(updatedStation.getFrequency());
                    radioStation.setUrl(updatedStation.getUrl());
                    radioStation.setPayments(updatedStation.getPayments());
                    radioStation.setPrograms(updatedStation.getPrograms());
                    radioStation.setAdverts(updatedStation.getAdverts());
                    return radioStationRepository.save(radioStation);
                })
                .orElse(null);

    }

}
