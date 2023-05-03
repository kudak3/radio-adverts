package com.example.radioadsapp.repository;


import com.example.radioadsapp.model.RadioStation;
import com.example.radioadsapp.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RadioStationRepository extends JpaRepository<RadioStation,Long> {
    RadioStation findRadioStationByName(String name);
    boolean existsRadioStationByCreatedBy(String email);


}
