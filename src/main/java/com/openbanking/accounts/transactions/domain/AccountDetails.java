package com.openbanking.accounts.transactions.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class AccountDetails {

    private String type;
    private String description;

    @JsonProperty("posted")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd\'T\'HH:mm:ss\'Z\'", timezone = "UTC")
    private Date postedDate;

    @JsonProperty("completed")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd\'T\'HH:mm:ss\'Z\'", timezone = "UTC")
    private Date completedDate;

    @JsonProperty("new_balance")
    private Balance newBalance;

    @JsonProperty("value")
    protected Balance value;
}
