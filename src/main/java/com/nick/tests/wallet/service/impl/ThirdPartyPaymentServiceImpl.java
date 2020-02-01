package com.nick.tests.wallet.service.impl;

import com.nick.tests.wallet.exception.ThirdPartyPaymentServiceException;
import com.nick.tests.wallet.service.ThirdPartyPaymentService;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;


/**
 * A real implementation would call to a third party's payment service (such as Stripe, Paypal, Redsys...).
 *
 * This is a dummy implementation which throws an error when trying to change less than 10€.
 */
@Service
public class ThirdPartyPaymentServiceImpl implements ThirdPartyPaymentService {
    private BigDecimal threshold = new BigDecimal(10);

    @Override
    public void charge(BigDecimal amount) throws ThirdPartyPaymentServiceException {
        if (amount.compareTo(threshold) < 0) {
            throw new ThirdPartyPaymentServiceException();
        }
    }
}
