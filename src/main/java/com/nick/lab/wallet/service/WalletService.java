package com.nick.lab.wallet.service;

import com.nick.lab.wallet.dto.DepositDTO;
import com.nick.lab.wallet.dto.PaymentDTO;
import com.nick.lab.wallet.dto.WalletDTO;

public interface WalletService {
    WalletDTO checkBalance(Long id) throws Exception;

    WalletDTO makePayment(PaymentDTO paymentDTO) throws Exception;

    WalletDTO addFunds(DepositDTO depositDTO) throws Exception;
}
