package io.malachai.homebar.extern.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.malachai.homebar.domain.AuthenticatedUser;
import io.malachai.homebar.domain.RawToken;
import io.malachai.homebar.domain.TokenGenerator;
import io.malachai.homebar.domain.TokenPair;
import io.malachai.homebar.domain.TokenParser;
import io.malachai.homebar.domain.TokenReader;
import io.malachai.homebar.extern.ExpiredAccessTokenException;
import io.malachai.homebar.extern.InvalidTokenException;
import java.util.Date;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtTokenUtil implements TokenGenerator, TokenParser, TokenReader {

    private static final String HEADER_PREFIX = "Bearer ";

    private final JwtTokenProperties properties;

    public TokenPair generate(AuthenticatedUser principal) {
        return new TokenPair(
                generateAccessToken(principal), generateRefreshToken(principal.email()));
    }

    private String generateAccessToken(AuthenticatedUser principal) {
        return Jwts.builder()
                .subject(principal.email())
                .claim("principal", principal)
                .issuedAt(new Date())
                .expiration(
                        new Date(
                                System.currentTimeMillis()
                                        + properties.accessTokenExpirationMillis()))
                .signWith(properties.secretKey())
                .compact();
    }

    private String generateRefreshToken(String email) {
        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(
                        new Date(
                                System.currentTimeMillis()
                                        + properties.refreshTokenExpirationMillis()))
                .signWith(properties.secretKey())
                .compact();
    }

    public RawToken parse(String tokenString) {
        try {
            Claims claims =
                    Jwts.parser()
                            .verifyWith(properties.secretKey())
                            .build()
                            .parseSignedClaims(tokenString)
                            .getPayload();
            return new RawToken(
                    claims.getSubject(),
                    claims.get("principal", AuthenticatedUser.class),
                    claims.getId(),
                    claims.getExpiration());
        } catch (ExpiredJwtException e) {
            throw new ExpiredAccessTokenException("인증 시간이 만료되었습니다. 다시 로그인 해주세요.");
        } catch (UnsupportedJwtException
                | MalformedJwtException
                | SignatureException
                | IllegalArgumentException e) {
            e.printStackTrace();
            throw new InvalidTokenException("인증 요청이 잘못되었습니다. 다시 로그인 해주세요.");
        }
    }

    public Optional<String> read(String header) {
        if (header == null || header.isBlank()) {
            return Optional.empty();
        } else if (header.length() < HEADER_PREFIX.length()) {
            return Optional.empty();
        } else {
            return Optional.of(header.substring(HEADER_PREFIX.length()));
        }
    }
}
