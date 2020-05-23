package com.openbanking.accounts.transactions.security;

import com.openbanking.accounts.transactions.domain.JwtUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class JwtGenerator {

    public String generate(JwtUser jwtUser) {

        log.info("JwtUser {} {} {}", jwtUser.getUserName(), jwtUser.getUserId(), jwtUser.getRole());
        Claims claims = Jwts.claims()
                .setSubject(jwtUser.getUserName());
        claims.put("userId", String.valueOf(jwtUser.getUserId()));
        claims.put("role", jwtUser.getRole());
        log.info("Generating Jwt Token");
        final String jwtToken = Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, "transactionKey")
                .compact();
        log.info("jwt generated :::: "+jwtToken);
        return jwtToken;
    }
}
