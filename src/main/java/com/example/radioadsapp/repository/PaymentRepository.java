package com.example.radioadsapp.repository;

import com.example.radioadsapp.model.Payment;
import com.example.radioadsapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {
    long countPaymentsByNewEntryIsTrue();

    @Transactional
    @Modifying
    @Query("UPDATE Payment p SET p.newEntry = false WHERE p.newEntry = true ")
    void updateAllPayments();
    List<Payment> findAllByCreatedBy(String email);
    List<Payment> findAllByRadioStation_CreatedBy(String email);

}
