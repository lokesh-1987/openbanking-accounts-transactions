package com.openbanking.accounts.transactions.controller;

import com.openbanking.accounts.transactions.exception.IncorrectSandBoxInputDetailException;
import com.openbanking.accounts.transactions.exception.OpenBankSandboxServiceException;
import com.openbanking.accounts.transactions.response.TransactionsResponse;
import com.openbanking.accounts.transactions.service.TransactionsServiceImpl;
import com.openbanking.accounts.transactions.transformer.TransactionTransformer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/obp/v1.2.1/")
@Slf4j
public class TransactionsController {

    @Autowired
    private TransactionsServiceImpl transactionsService;

    @GetMapping(value = "banks/{bankId}/accounts/{accountId}/{viewId}/transactions")
    public TransactionsResponse getAccountsTransactions(@PathVariable("bankId") String bankId,
                                                        @PathVariable("accountId") String accountId,
                                                        @PathVariable("viewId") String viewId) throws OpenBankSandboxServiceException, IncorrectSandBoxInputDetailException {
        log.info("Received getAccountsTransactions request with bank id {}, accountId {} and viewId {}", bankId, accountId, viewId);
        return new TransactionTransformer().apply(transactionsService.getTransactions(bankId, accountId, viewId));
    }
}
