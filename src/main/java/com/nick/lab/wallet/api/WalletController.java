package com.nick.lab.wallet.api;

import com.nick.lab.wallet.dto.DepositDTO;
import com.nick.lab.wallet.dto.PaymentDTO;
import com.nick.lab.wallet.dto.WalletDTO;
import com.nick.lab.wallet.service.WalletService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Nicolás Tobalina
 */
@RestController
@RequestMapping("/api")
public class WalletController {

    @Autowired
    private WalletService walletService;

    private final Logger log = LoggerFactory.getLogger(WalletController.class);

    private static final String URL_BASE = "/wallet";

    /**
     * Check the balance of the requested wallet with its id.
     *
     * @param id the id of the wallet wanted
     * @return the response entity with the requested wallet
     * @throws Exception if an error occurs
     */
    @GetMapping(URL_BASE + "/check-balance")
    ResponseEntity<WalletDTO> checkBalance(@RequestParam(value = "id") Long id) throws Exception {
        log.info("GET request to check the balance of the wallet with id = " + id);
        return new ResponseEntity<>(walletService.checkBalance(id), HttpStatus.OK);
    }

    /**
     * Make a payment with the money of a selected wallet.
     *
     * @param paymentDTO the DTO with the wallet id, the amount to pay and a transaction comment
     * @return the response entity with the involved wallet
     * @throws Exception if an error occurs
     */
    @PostMapping(URL_BASE + "/make-payment")
    ResponseEntity<WalletDTO> makePayment(@Valid @RequestBody PaymentDTO paymentDTO) throws Exception {
        log.info("POST request to make a payment with the wallet with id = " + paymentDTO.getId());
        return new ResponseEntity<>(walletService.makePayment(paymentDTO), HttpStatus.CREATED);
    }

    /**
     * Add funds to a wallet with a selected third party payment gateway.
     *
     * @param depositDTO the DTO with the wallet id, the amount to deposit and the selected payment gateway
     * @return the response entity with the involved wallet
     * @throws Exception if an error occurs
     */
    @PostMapping(URL_BASE + "/add-funds")
    ResponseEntity<WalletDTO> addFunds(@Valid @RequestBody DepositDTO depositDTO) throws Exception {
        log.info("POST request to add funds to the wallet with id = " + depositDTO.getId() + ".");
        return new ResponseEntity<>(walletService.addFunds(depositDTO), HttpStatus.CREATED);
    }
}
