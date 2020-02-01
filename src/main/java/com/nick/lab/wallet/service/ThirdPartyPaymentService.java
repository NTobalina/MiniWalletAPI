package com.nick.lab.wallet.service;

import com.nick.lab.wallet.exception.ThirdPartyPaymentServiceException;

import java.math.BigDecimal;

public interface ThirdPartyPaymentService {
    void charge(BigDecimal amount) throws ThirdPartyPaymentServiceException;
}
