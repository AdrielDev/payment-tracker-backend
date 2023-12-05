package com.api.paymenttracke.dto.payment;

import java.time.LocalDate;

public class PaymentRequestDTO {
    private Long id;
    private double amount;
    private LocalDate dueDate;
    private String status;
    private Long recurringPaymentId;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public LocalDate getDueDate() {
        return dueDate;
    }
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Long getRecurringPaymentId() {
        return recurringPaymentId;
    }
    public void setRecurringPaymentId(Long recurringPaymentId) {
        this.recurringPaymentId = recurringPaymentId;
    }
}
