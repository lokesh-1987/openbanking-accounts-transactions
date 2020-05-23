package com.openbanking.accounts.transactions.controller;

import com.openbanking.accounts.transactions.domain.JwtUser;
import com.openbanking.accounts.transactions.security.JwtGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/token")
@Slf4j
public class TokenController {

    @Autowired
    private JwtGenerator jwtGenerator;

    @PostMapping
    public String generateToken(@RequestBody final JwtUser jwtUser) {
        log.debug("JwtUser {} {} {}", jwtUser.getUserName(), jwtUser.getUserId(), jwtUser.getRole());
        return jwtGenerator.generate(jwtUser);
    }
}
