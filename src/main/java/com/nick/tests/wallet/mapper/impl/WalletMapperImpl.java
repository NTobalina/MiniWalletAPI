package com.nick.tests.wallet.mapper.impl;

import com.nick.tests.wallet.dto.WalletDTO;
import com.nick.tests.wallet.entity.Wallet;
import com.nick.tests.wallet.mapper.WalletMapper;
import org.springframework.stereotype.Component;

@Component
public class WalletMapperImpl implements WalletMapper {

    @Override
    public Wallet toEntity(WalletDTO walletDTO) {
        Wallet wallet = new Wallet();
        wallet.setId(walletDTO.getId());
        wallet.setAmount(walletDTO.getAmount());
        wallet.setName(walletDTO.getName());
        return wallet;
    }

    @Override
    public WalletDTO toDTO(Wallet wallet) {
        WalletDTO walletDTO = new WalletDTO();
        walletDTO.setId(wallet.getId());
        walletDTO.setAmount(wallet.getAmount());
        walletDTO.setName(wallet.getName());
        return walletDTO;
    }
}
