package com.example.radioadsapp.repository;

import com.example.radioadsapp.model.Advertiser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AdvertiserRepository extends JpaRepository<Advertiser,Long> {

    long countAdvertisersByNewEntryIsTrue();

    @Transactional
    @Modifying
    @Query("UPDATE  Advertiser a SET a.newEntry = false WHERE a.newEntry = true ")
    void updateAllAdvertisers();

    Advertiser findAdvertiserByUserId(Long id);
}
