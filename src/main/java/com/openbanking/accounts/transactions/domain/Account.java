package com.openbanking.accounts.transactions.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Account {

    private String id;

    @JsonProperty("number")
    private String accountNumber;

    private List<AccountHolder> holders;

    @JsonProperty("kind")
    private String type;

    @JsonProperty("IBAN")
    private String iban;

    @JsonProperty("swift_bic")
    private String bic;

    private Bank bank;

    @JsonProperty("metadata")
    private AccountsMetaData accountsMetadata;
}
