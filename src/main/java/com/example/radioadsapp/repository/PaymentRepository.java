package com.example.radioadsapp.repository;

import com.example.radioadsapp.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {
    long countPaymentsByNewEntryTrue();

    @Transactional
    @Modifying
    @Query("UPDATE Payment p SET p.newEntry = false WHERE p.newEntry = true ")
    void updateAllPayments();
}
