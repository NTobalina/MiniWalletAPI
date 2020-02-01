package com.nick.tests.wallet.service;

import com.nick.tests.wallet.dto.DepositDTO;
import com.nick.tests.wallet.dto.PaymentDTO;
import com.nick.tests.wallet.dto.WalletDTO;

public interface WalletService {
    WalletDTO checkBalance(Long id) throws Exception;

    WalletDTO makePayment(PaymentDTO paymentDTO) throws Exception;

    WalletDTO addFunds(DepositDTO depositDTO) throws Exception;
}
