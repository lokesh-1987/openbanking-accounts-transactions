package com.openbanking.accounts.transactions.domain;

import lombok.Data;

@Data
public class JwtUser {
    private String userName;
    private long userId;
    private String role;
}

