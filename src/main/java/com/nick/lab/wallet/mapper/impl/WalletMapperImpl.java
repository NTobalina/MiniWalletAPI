package com.nick.lab.wallet.mapper.impl;

import com.nick.lab.wallet.dto.WalletDTO;
import com.nick.lab.wallet.entity.Wallet;
import com.nick.lab.wallet.mapper.WalletMapper;
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
