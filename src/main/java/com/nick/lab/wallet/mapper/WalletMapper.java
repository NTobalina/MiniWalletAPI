package com.nick.lab.wallet.mapper;

import com.nick.lab.wallet.dto.WalletDTO;
import com.nick.lab.wallet.entity.Wallet;

public interface WalletMapper {
    Wallet toEntity(WalletDTO walletDTO);

    WalletDTO toDTO(Wallet wallet);
}
