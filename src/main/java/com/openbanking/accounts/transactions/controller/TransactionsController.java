package com.openbanking.accounts.transactions.controller;

import com.openbanking.accounts.transactions.domain.Transactions;
import com.openbanking.accounts.transactions.exception.IncorrectSandBoxInputDetailException;
import com.openbanking.accounts.transactions.exception.OpenBankSandboxServiceException;
import com.openbanking.accounts.transactions.response.TransactionsResponse;
import com.openbanking.accounts.transactions.service.TransactionsServiceImpl;
import com.openbanking.accounts.transactions.transformer.TransactionTransformer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
        log.info("Received getAccountsTransactions request with bank id - {}, accountId - {} and viewId - {}", bankId, accountId, viewId);
        return new TransactionTransformer().apply(transactionsService.getTransactions(bankId, accountId, viewId));
    }

    @GetMapping(value = "banks/{bankId}/accounts/{accountId}/{viewId}/transactions/filter", params = "transactionType")
    public TransactionsResponse getAccountsTransactionsBasedOnType(@PathVariable("bankId") String bankId,
                                                                   @PathVariable("accountId") String accountId,
                                                                   @PathVariable("viewId") String viewId,
                                                                   @RequestParam("transactionType") String transactionType)
            throws OpenBankSandboxServiceException,IncorrectSandBoxInputDetailException {
        log.info("Received getAccountsTransactionsBasedOnType request with bank id - {}, accountId - {} and viewId - {} and transactionType - {}", bankId, accountId, viewId, transactionType);
        final List<Transactions> transactions = new TransactionTransformer()
                .apply(transactionsService.getTransactions(bankId, accountId, viewId)).getTransactions()
                .stream()
                .filter(transaction -> transaction != null && transactionType.equals(transaction.getTransactionType()))
                .collect(Collectors.toList());
        return new TransactionsResponse().setTransactions(transactions);
    }

    @GetMapping(value = "banks/{bankId}/accounts/{accountId}/{viewId}/transactions/totalAmount", params = "transactionType")
    public double getTotalAmountBasedOnTransactionType(@PathVariable("bankId") String bankId,
                                                       @PathVariable("accountId") String accountId,
                                                       @PathVariable("viewId") String viewId,
                                                       @RequestParam("transactionType") String transactionType)
            throws OpenBankSandboxServiceException, IncorrectSandBoxInputDetailException {
        log.info("Received getTotalAmountBasedOnTransactionType request with bank id - {}, accountId - {} and viewId - {} and transactionType -{}", bankId, accountId, viewId, transactionType);
        return new TransactionTransformer()
                .apply(transactionsService.getTransactions(bankId, accountId, viewId)).getTransactions()
                .stream()
                .filter(transaction -> transaction != null && transactionType.equals(transaction.getTransactionType()))
                .map(transaction -> transaction.getTransactionAmount())
                .collect(Collectors.summingDouble(Double::doubleValue));
    }

}
