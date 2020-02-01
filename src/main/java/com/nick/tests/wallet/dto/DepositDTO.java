package com.nick.tests.wallet.dto;

import com.nick.tests.wallet.enumerate.ThirdPartyPaymentGateway;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class DepositDTO {
    @NotNull
    private Long id;

    @NotNull
    private BigDecimal amount;

    @NotNull
    private ThirdPartyPaymentGateway thirdPartyPaymentGateway;

    public Long getId() {
        return id;
    }

    public DepositDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public DepositDTO setAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public ThirdPartyPaymentGateway getThirdPartyPaymentGateway() {
        return thirdPartyPaymentGateway;
    }

    public DepositDTO setThirdPartyPaymentGateway(ThirdPartyPaymentGateway thirdPartyPaymentGateway) {
        this.thirdPartyPaymentGateway = thirdPartyPaymentGateway;
        return this;
    }
}
