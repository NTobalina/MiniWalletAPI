package com.nick.tests.wallet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nick.tests.wallet.api.WalletController;
import com.nick.tests.wallet.dto.DepositDTO;
import com.nick.tests.wallet.dto.PaymentDTO;
import com.nick.tests.wallet.enumerate.ThirdPartyPaymentGateway;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WalletApplication.class)
@ActiveProfiles(profiles = "test")
public class WalletApplicationIT {

    @Autowired
    WalletController walletController;

    @Autowired
    ObjectMapper objectMapper;

    private MockMvc restWalletControllerMockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.restWalletControllerMockMvc = MockMvcBuilders.standaloneSetup(walletController).build();
    }

    @Test
    public void checkBalanceOK() throws Exception {
        Long id = 1L;

        restWalletControllerMockMvc.perform(get("/api/wallet/check-balance").param("id", String.valueOf(id))).andExpect(status().isOk());
    }

	@Test(expected = Exception.class) // Id not found
	public void checkBalanceKO() throws Exception {
        Long id = 5L;

        restWalletControllerMockMvc.perform(get("/api/wallet/check-balance").param("id", String.valueOf(id))).andExpect(status().isOk());
	}

    @Test
    public void makePaymentOK() throws Exception {
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setId(1L);
        paymentDTO.setAmount(new BigDecimal(5.437F).setScale(2, BigDecimal.ROUND_HALF_EVEN));
        paymentDTO.setComment("Cool payment test!");

        restWalletControllerMockMvc.perform(post("/api/wallet/make-payment").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(paymentDTO))).andExpect(status().isCreated());
    }

    @Test(expected = Exception.class) // ID not found
    public void makePaymentKO_1() throws Exception {
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setId(100L);
        paymentDTO.setAmount(new BigDecimal(5.437F).setScale(2, BigDecimal.ROUND_HALF_EVEN));
        paymentDTO.setComment("Even cooler payment test!");

        restWalletControllerMockMvc.perform(post("/api/wallet/make-payment").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(paymentDTO))).andExpect(status().isCreated());
    }

    @Test(expected = Exception.class) // Not enough money in the wallet
    public void makePaymentKO_2() throws Exception {
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setId(1L);
        paymentDTO.setAmount(new BigDecimal(99999.99F).setScale(2, BigDecimal.ROUND_HALF_EVEN));
        paymentDTO.setComment("The coolest payment test!");

        restWalletControllerMockMvc.perform(post("/api/wallet/make-payment").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(paymentDTO))).andExpect(status().isCreated());
    }

    @Test(expected = Exception.class) // Negative amount
    public void makePaymentKO_3() throws Exception {
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setId(1L);
        paymentDTO.setAmount(new BigDecimal(-1));
        paymentDTO.setComment("-1(cool) payment test!");

        restWalletControllerMockMvc.perform(post("/api/wallet/make-payment").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(paymentDTO))).andExpect(status().isCreated());
    }

    @Test
    public void addFundsOK() throws Exception {
        DepositDTO depositDTO = new DepositDTO();
        depositDTO.setId(1L);
        depositDTO.setAmount(new BigDecimal(20));
        depositDTO.setThirdPartyPaymentGateway(ThirdPartyPaymentGateway.PAYPAL);

        restWalletControllerMockMvc.perform(post("/api/wallet/add-funds").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(depositDTO))).andExpect(status().isCreated());
    }

    @Test(expected = Exception.class) // It has not reached the threshold
    public void addFundsKO_1() throws Exception {
        DepositDTO depositDTO = new DepositDTO();
        depositDTO.setId(1L);
        depositDTO.setAmount(new BigDecimal(4));
        depositDTO.setThirdPartyPaymentGateway(ThirdPartyPaymentGateway.PAYPAL);

        restWalletControllerMockMvc.perform(post("/api/wallet/add-funds").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(depositDTO))).andExpect(status().isCreated());
    }

    @Test(expected = AssertionError.class) // Payment gateway not specified
    public void addFundsKO_2() throws Exception {
        DepositDTO depositDTO = new DepositDTO();
        depositDTO.setId(1L);
        depositDTO.setAmount(new BigDecimal(4));
        depositDTO.setThirdPartyPaymentGateway(null);

        restWalletControllerMockMvc.perform(post("/api/wallet/add-funds").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(depositDTO))).andExpect(status().isCreated());
    }

    @Test(expected = Exception.class) // ID not found
    public void addFundsKO_3() throws Exception {
        DepositDTO depositDTO = new DepositDTO();
        depositDTO.setId(18L);
        depositDTO.setAmount(new BigDecimal(20));
        depositDTO.setThirdPartyPaymentGateway(ThirdPartyPaymentGateway.PAYPAL);

        restWalletControllerMockMvc.perform(post("/api/wallet/add-funds").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(depositDTO))).andExpect(status().isCreated());
    }

    @Test(expected = Exception.class) // Negative amount
    public void addFundsKO_4() throws Exception {
        DepositDTO depositDTO = new DepositDTO();
        depositDTO.setId(1L);
        depositDTO.setAmount(new BigDecimal(-8));
        depositDTO.setThirdPartyPaymentGateway(ThirdPartyPaymentGateway.PAYPAL);

        restWalletControllerMockMvc.perform(post("/api/wallet/add-funds").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(depositDTO))).andExpect(status().isCreated());
    }
}
