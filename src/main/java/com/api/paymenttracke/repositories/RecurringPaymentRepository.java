package com.api.paymenttracke.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.paymenttracke.models.RecurringPayment;

@Repository
public interface RecurringPaymentRepository extends JpaRepository<RecurringPayment, Long> {
}
