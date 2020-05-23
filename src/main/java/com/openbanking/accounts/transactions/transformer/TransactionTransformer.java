package com.openbanking.accounts.transactions.transformer;

import com.openbanking.accounts.transactions.domain.*;
import com.openbanking.accounts.transactions.response.SandBoxTransactionsResponse;
import com.openbanking.accounts.transactions.response.TransactionsResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
public class TransactionTransformer implements Function<SandBoxTransactionsResponse, TransactionsResponse> {

    @Override
    public TransactionsResponse apply(final SandBoxTransactionsResponse sandBoxTransactionsResponse) {
        log.info("Transforming the open bank sandbox response to transaction response");
        final List<SandBoxTransactions> sandBoxTransactions = sandBoxTransactionsResponse.getTransactions();
        return new TransactionsResponse()
                .setTransactions(sandBoxTransactions
                                    .stream()
                                    .map(sandBoxTransaction -> toTransactions(sandBoxTransaction))
                                    .collect(Collectors.toList()));
    }

    private Transactions toTransactions(final SandBoxTransactions sandBoxTransaction) {
        final Account targetAccount = sandBoxTransaction.getTargetAccount();
        final Account ownAccount = sandBoxTransaction.getOwnAccount();
        final List<AccountHolder> holders = targetAccount != null ? targetAccount.getHolders() : null;
        final AccountHolder accountHolder = holders != null ? holders.get(0) : null;
        final AccountsMetaData accountsMetaData = targetAccount != null ? targetAccount.getAccountsMetadata() : null;
        final AccountDetails accountDetails = sandBoxTransaction.getDetails();
        final Balance balance = accountDetails != null ? accountDetails.getValue() : null;
        return new Transactions()
                .setId(sandBoxTransaction.getId())
                .setAccountId(ownAccount != null ? ownAccount.getId() : null)
                .setCounterPartyAccount(targetAccount != null ? targetAccount.getAccountNumber() : null)
                .setCounterPartyName(accountHolder != null ? accountHolder.getName() : null)
                .setCounterPartyLogoPath(accountsMetaData != null ? accountsMetaData.getImageUrl() : null)
                .setInstructedAmount(balance != null ? balance.getAmount() : null)
                .setInstructedCurrency(balance != null ? balance.getCurrency() : null)
                .setTransactionAmount(balance != null ? balance.getAmount() : null)
                .setTransactionCurrency(balance != null ? balance.getCurrency() : null)
                .setTransactionType(accountDetails != null ? accountDetails.getType() : null)
                .setDescription(accountDetails != null ? accountDetails.getDescription() : null);
    }
}
