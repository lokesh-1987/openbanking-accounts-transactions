package com.openbanking.accounts.transactions.response;

import com.openbanking.accounts.transactions.domain.SandBoxTransactions;
import lombok.Data;

import java.util.List;

@Data
public class SandBoxTransactionsResponse {
    private List<SandBoxTransactions> transactions;
}
