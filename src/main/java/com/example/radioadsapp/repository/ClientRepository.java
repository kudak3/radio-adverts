package com.example.radioadsapp.repository;

import com.example.radioadsapp.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {

    long countClientsByNewEntryIsTrue();

    @Transactional
    @Modifying
    @Query("UPDATE  Client a SET a.newEntry = false WHERE a.newEntry = true ")
    void updateAllClients();

    Client findClientByUserId(Long id);
}
