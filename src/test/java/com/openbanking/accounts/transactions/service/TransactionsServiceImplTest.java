package com.openbanking.accounts.transactions.service;

import com.openbanking.accounts.transactions.exception.IncorrectSandBoxInputDetailException;
import com.openbanking.accounts.transactions.response.SandBoxTransactionsResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionsServiceImplTest {

    @Autowired
    private TransactionsService transactionsService;

    @Test
    public void testGetTransactions() {
        final SandBoxTransactionsResponse sandBoxTransactionsResponse = transactionsService.
                getTransactions("rbs","savings-kids-john","public");
        assertNotNull(sandBoxTransactionsResponse);
    }

    @Test(expected = IncorrectSandBoxInputDetailException.class)
    public void testGetTransactionsWhenAccountIdIsWrong() {
        transactionsService.getTransactions("rbs","Lokesh","public");
    }

    @Test(expected = IncorrectSandBoxInputDetailException.class)
    public void testGetTransactionsWhenBankIdIsWrong() {
        transactionsService.getTransactions("Lokesh","savings-kids-john","public");
    }

    @Test(expected = IncorrectSandBoxInputDetailException.class)
    public void testGetTransactionsWhenViewIdIsWrong() {
        transactionsService.getTransactions("rbs","savings-kids-john","Lokesh");
    }
}
