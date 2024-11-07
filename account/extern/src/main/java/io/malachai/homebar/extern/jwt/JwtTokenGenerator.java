package io.malachai.homebar.extern.jwt;

import io.jsonwebtoken.Jwts;
import io.malachai.homebar.domain.TokenGenerator;
import io.malachai.homebar.domain.TokenPair;
import java.util.Date;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtTokenGenerator implements TokenGenerator {

    private final JwtTokenProperties properties;

    public TokenPair generate(String email) {
        return new TokenPair(generateAccessToken(email), generateRefreshToken(email));
    }

    private String generateAccessToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(
                        new Date(
                                System.currentTimeMillis()
                                        + properties.accessTokenExpirationMillis()))
                .signWith(properties.signatureAlgorithm(), properties.secretKey())
                .compact();
    }

    private String generateRefreshToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(
                        new Date(
                                System.currentTimeMillis()
                                        + properties.refreshTokenExpirationMillis()))
                .signWith(properties.signatureAlgorithm(), properties.secretKey())
                .compact();
    }
}
