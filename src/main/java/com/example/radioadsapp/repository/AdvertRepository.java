package com.example.radioadsapp.repository;

import com.example.radioadsapp.model.Advert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdvertRepository extends JpaRepository<Advert,Long> {
    Optional<Advert> findAdvertByTitle(String title);
    List<Advert> findAllByCreatedBy(String email);
    List<Advert> findAllByRadioStation_CreatedBy(String email);
    long countAllByNewEntryIsTrue();
}
