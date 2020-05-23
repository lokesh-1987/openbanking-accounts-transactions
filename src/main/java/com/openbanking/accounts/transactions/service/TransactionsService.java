package com.openbanking.accounts.transactions.service;

import com.openbanking.accounts.transactions.exception.IncorrectSandBoxInputDetailException;
import com.openbanking.accounts.transactions.exception.OpenBankSandboxServiceException;
import com.openbanking.accounts.transactions.response.SandBoxTransactionsResponse;

public interface TransactionsService  {

    SandBoxTransactionsResponse getTransactions(String bankId, String accountId, String viewId) throws OpenBankSandboxServiceException, IncorrectSandBoxInputDetailException;
}
