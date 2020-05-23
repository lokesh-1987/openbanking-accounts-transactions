package com.openbanking.accounts.transactions.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Bank {

    private String name;

    @JsonProperty("national_identifier")
    private String identifier;
}
