package com.nick.tests.wallet.dto;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class PaymentDTO {
    @NotNull
    private Long id;

    @NotNull
    private BigDecimal amount;

    private String comment;

    public Long getId() {
        return id;
    }

    public PaymentDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public PaymentDTO setAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public PaymentDTO setComment(String comment) {
        this.comment = comment;
        return this;
    }
}
