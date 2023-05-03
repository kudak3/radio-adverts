package com.example.radioadsapp.repository;

import com.example.radioadsapp.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {

    long countClientsByNewEntryIsTrue();

    @Transactional
    @Modifying
    @Query("UPDATE  Client a SET a.newEntry = false WHERE a.newEntry = true ")
    void updateAllClients();
//    @Query("SELECT c FROM Client c JOIN c.adverts a WHERE a.radioStation.id = :radioStationId")
//    List<Client> findByRadioStationId(@Param("radioStationId") Long radioStationId);

}
