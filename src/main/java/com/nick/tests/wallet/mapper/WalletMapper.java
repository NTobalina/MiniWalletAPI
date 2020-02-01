package com.nick.tests.wallet.mapper;

import com.nick.tests.wallet.dto.WalletDTO;
import com.nick.tests.wallet.entity.Wallet;

public interface WalletMapper {
    Wallet toEntity(WalletDTO walletDTO);

    WalletDTO toDTO(Wallet wallet);
}
