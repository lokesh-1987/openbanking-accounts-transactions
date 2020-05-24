package com.openbanking.accounts.transactions.security;

import com.openbanking.accounts.transactions.domain.JwtUser;
import com.openbanking.accounts.transactions.exception.JwtValidationException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class JwtValidator {

    @Value("${open.banking.accounts.transaction.signed.key}")
    private String secretKey;

    public JwtUser validate(String token) {

        JwtUser jwtUser = null;
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody();

            jwtUser = new JwtUser();

            jwtUser.setUserName(body.getSubject());
            jwtUser.setUserId(Long.parseLong((String) body.get("userId")));
            jwtUser.setRole((String) body.get("role"));
        }
        catch (Exception e) {
            log.error("Exception occurred while validating the Jwt"+ e.getMessage());
            throw new JwtValidationException("Jwt validation exception");
        }

        return jwtUser;
    }
}
