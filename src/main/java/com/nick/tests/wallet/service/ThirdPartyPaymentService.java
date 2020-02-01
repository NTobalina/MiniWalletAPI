package com.nick.tests.wallet.service;

import com.nick.tests.wallet.exception.ThirdPartyPaymentServiceException;

import java.math.BigDecimal;

public interface ThirdPartyPaymentService {
    void charge(BigDecimal amount) throws ThirdPartyPaymentServiceException;
}
