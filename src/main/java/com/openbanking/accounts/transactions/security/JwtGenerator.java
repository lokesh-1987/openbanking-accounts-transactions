package com.openbanking.accounts.transactions.security;

import com.openbanking.accounts.transactions.domain.JwtUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class JwtGenerator {

    @Value("${open.banking.accounts.transaction.signed.key}")
    private String secretKey;

    public String generate(JwtUser jwtUser) {

        log.info("JwtUser details - {}, {}, {}", jwtUser.getUserName(), jwtUser.getUserId(), jwtUser.getRole());
        Claims claims = Jwts.claims()
                .setSubject(jwtUser.getUserName());
        claims.put("userId", String.valueOf(jwtUser.getUserId()));
        claims.put("role", jwtUser.getRole());
        log.info("Generating Jwt Token");
        final String jwtToken = Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
        log.info("jwt generated :::: "+jwtToken);
        return jwtToken;
    }
}
