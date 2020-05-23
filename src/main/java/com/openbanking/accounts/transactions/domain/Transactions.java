package com.openbanking.accounts.transactions.domain;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Transactions {

    private String id;
    private String accountId;
    private String counterPartyAccount;
    private String counterPartyName;
    private String counterPartyLogoPath;
    private double instructedAmount;
    private String instructedCurrency;
    private double transactionAmount;
    private String transactionCurrency;
    private String transactionType;
    private String description;
}
