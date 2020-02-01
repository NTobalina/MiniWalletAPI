package com.nick.lab.wallet.dto;

import java.math.BigDecimal;

public class WalletDTO {
    private Long id;

    private BigDecimal amount;

    private String name;

    public Long getId() {
        return id;
    }

    public WalletDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public WalletDTO setAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public String getName() {
        return name;
    }

    public WalletDTO setName(String name) {
        this.name = name;
        return this;
    }
}
