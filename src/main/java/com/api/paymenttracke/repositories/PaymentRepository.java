package com.api.paymenttracke.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.paymenttracke.models.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
