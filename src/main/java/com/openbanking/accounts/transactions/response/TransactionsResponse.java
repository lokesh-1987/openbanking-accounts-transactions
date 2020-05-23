package com.openbanking.accounts.transactions.response;

import com.openbanking.accounts.transactions.domain.Transactions;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class TransactionsResponse {
    private List<Transactions> transactions;
}
