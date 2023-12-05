package com.api.paymenttracke.dto.recurringpayment;

import java.time.LocalDateTime;

public class RecurringPaymentRequestDTO {
    private Double installmentAmount;
    private Double installmentValue;
    private Integer totalInstallments;
    private LocalDateTime registerDate;
    private String status;
    private String description;
    private Long id;
    private Long payeeId;
    private Long payerId;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        RecurringPaymentRequestDTO other = (RecurringPaymentRequestDTO) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getPayeeId() {
        return payeeId;
    }
    public void setPayeeId(Long payeeId) {
        this.payeeId = payeeId;
    }
    public Long getPayerId() {
        return payerId;
    }
    public void setPayerId(Long payerId) {
        this.payerId = payerId;
    }
    public Double getInstallmentAmount() {
        return installmentAmount;
    }
    public void setInstallmentAmount(Double installmentAmount) {
        this.installmentAmount = installmentAmount;
    }
    public Double getInstallmentValue() {
        return installmentValue;
    }
    public void setInstallmentValue(Double installmentValue) {
        this.installmentValue = installmentValue;
    }
    public Integer getTotalInstallments() {
        return totalInstallments;
    }
    public void setTotalInstallments(Integer totalInstallments) {
        this.totalInstallments = totalInstallments;
    }
    public LocalDateTime getRegisterDate() {
        return registerDate;
    }
    public void setRegisterDate(LocalDateTime registerDate) {
        this.registerDate = registerDate;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
