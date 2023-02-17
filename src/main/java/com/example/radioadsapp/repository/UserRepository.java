package com.example.radioadsapp.repository;


import com.example.radioadsapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);
    long countUsersByNewEntryIsTrue();

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.newEntry = false WHERE u.newEntry = true ")
    void updateAllUsers();


}
