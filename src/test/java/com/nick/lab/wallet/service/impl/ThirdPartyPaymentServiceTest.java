package com.nick.lab.wallet.service.impl;

import com.nick.lab.wallet.exception.ThirdPartyPaymentServiceException;
import org.junit.Test;

import java.math.BigDecimal;

public class ThirdPartyPaymentServiceTest {

    ThirdPartyPaymentServiceImpl s = new ThirdPartyPaymentServiceImpl();

    @Test(expected = ThirdPartyPaymentServiceException.class)
    public void test_exception() throws ThirdPartyPaymentServiceException {
        s.charge(new BigDecimal(5));
    }

    @Test
    public void test_ok() throws ThirdPartyPaymentServiceException {
        s.charge(new BigDecimal(15));
    }
}
