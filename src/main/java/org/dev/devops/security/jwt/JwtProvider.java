package org.dev.devops.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Log4j2
@Component
@RequiredArgsConstructor
public class JwtProvider {

    @Value("${jwt.secret}")
    private String secretKey;

    public String generateToken () {
        Date expirationDate = getExpirationDate();
        return Jwts.builder()
                .issuedAt(new Date())
                .expiration(expirationDate)
                .signWith(getKey(secretKey))
                .compact();
    }

    private SecretKey getKey(String secret) {
        byte[] secretBytes = Decoders.BASE64URL.decode(secret);
        return Keys.hmacShaKeyFor(secretBytes);
    }

    public Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(getKey(secretKey))
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    /**
     * Generate an expiration date for the token of 8H.
     *
     * @return date.
     */
    private Date getExpirationDate() {
        return new Date(new Date().getTime() + 480 * 60000L);
    }

}
