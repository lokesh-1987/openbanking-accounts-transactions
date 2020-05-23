package com.openbanking.accounts.transactions.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AccountHolder {
    private String name;

    @JsonProperty("is_public")
    private Boolean isPublic;
}
