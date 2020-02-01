package com.nick.tests.wallet.service.impl;

import com.nick.tests.wallet.dto.DepositDTO;
import com.nick.tests.wallet.dto.PaymentDTO;
import com.nick.tests.wallet.dto.WalletDTO;
import com.nick.tests.wallet.entity.Wallet;
import com.nick.tests.wallet.enumerate.ThirdPartyPaymentGateway;
import com.nick.tests.wallet.mapper.WalletMapper;
import com.nick.tests.wallet.repository.WalletRepository;
import com.nick.tests.wallet.service.ThirdPartyPaymentService;
import com.nick.tests.wallet.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class WalletServiceImpl implements WalletService {

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private WalletMapper walletMapper;

    @Autowired
    private ThirdPartyPaymentService thirdPartyPaymentService;

    @Override
    @Transactional(readOnly = true)
    public WalletDTO checkBalance(Long id) throws Exception {
        Wallet wallet = walletRepository.findOne(id);

        if (wallet == null) {
            throw new Exception("Wallet not found with id = " + id + ".");
        }

        return walletMapper.toDTO(walletRepository.save(wallet));
    }

    @Override
    @Transactional
    public WalletDTO makePayment(PaymentDTO paymentDTO) throws Exception {
        if (paymentDTO == null) {
            throw new Exception("paymentDTO can not be null.");
        }

        if (paymentDTO.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new Exception("The amount to pay must be greater than 0.");
        }

        Wallet wallet = walletRepository.findOne(paymentDTO.getId());

        if (wallet == null) {
            throw new Exception("Wallet not found with id = " + paymentDTO.getId() + ".");
        }

        if (paymentDTO.getAmount().compareTo(wallet.getAmount()) > 0) {
            throw new Exception("The amout to pay (" + paymentDTO.getAmount() + "€) can not be higher than the wallet funds (" + wallet.getAmount() + "€).");
        }

        wallet.setAmount(wallet.getAmount().subtract(paymentDTO.getAmount()).setScale(2, BigDecimal.ROUND_HALF_EVEN));

        return walletMapper.toDTO(walletRepository.save(wallet));
    }

    @Override
    @Transactional
    public WalletDTO addFunds(DepositDTO depositDTO) throws Exception {
        if (depositDTO == null) {
            throw new Exception("depositDTO can not be null.");
        }

        if (depositDTO.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new Exception("The adding amount must be greater than 0.");
        }

        Wallet wallet = walletRepository.findOne(depositDTO.getId());

        if (wallet == null) {
            throw new Exception("Wallet not found with id = " + depositDTO.getId() + ".");
        }

        switch (depositDTO.getThirdPartyPaymentGateway()) {
            case STRIPE:
            case PAYPAL:
            case REDSYS:
                thirdPartyPaymentService.charge(depositDTO.getAmount());
                break;
            default:
                throw new Exception("The introduced payment gateway (" + depositDTO.getThirdPartyPaymentGateway() + ") does not correspond to any of the supported list: " + ThirdPartyPaymentGateway.values().toString() + ".");
        }

        wallet.setAmount(wallet.getAmount().add(depositDTO.getAmount()).setScale(2, BigDecimal.ROUND_HALF_EVEN));

        return walletMapper.toDTO(walletRepository.save(wallet));
    }
}
