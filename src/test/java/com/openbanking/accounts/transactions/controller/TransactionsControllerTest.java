package com.openbanking.accounts.transactions.controller;

import com.openbanking.accounts.transactions.response.TransactionsResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TransactionsControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetAccountsTransactions() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        final ResponseEntity<TransactionsResponse> response = restTemplate.exchange(
                createURLWithPort("/obp/v1.2.1/banks/rbs/accounts/savings-kids-john/public/transactions"),
                HttpMethod.GET, new HttpEntity<String>(headers), TransactionsResponse.class);
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertThat(response.getStatusCodeValue(), is(200));
    }

    @Test
    public void testGetAccountsTransactionsBasedOnType() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        final ResponseEntity<TransactionsResponse> response = restTemplate.exchange(
                createURLWithPort("/obp/v1.2.1/banks/rbs/accounts/savings-kids-john/public/transactions?transactionType=sandbox-payment"),
                HttpMethod.GET, new HttpEntity<String>(headers), TransactionsResponse.class);
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertThat(response.getStatusCodeValue(), is(200));
    }

    @Test
    public void testGetTotalAmountBasedOnTransactionType() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        final ResponseEntity<Double> response = restTemplate.exchange(
                createURLWithPort("/obp/v1.2.1/banks/rbs/accounts/savings-kids-john/public/transactions/totalAmount?transactionType=sandbox-payment"),
                HttpMethod.GET, new HttpEntity<String>(headers), Double.class);
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertThat(response.getStatusCodeValue(), is(200));
    }

    private String createURLWithPort(final String uri) {
        return "http://localhost:" + port + uri;
    }
}