package io.malachai.homebar.extern.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.malachai.homebar.extern.ExpiredAccessTokenException;
import io.malachai.homebar.extern.InvalidAccessTokenException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtTokenParser {

    private final JwtTokenProperties properties;

    public JwtRawToken parse(String tokenString) {
        try {
            Claims claims =
                    Jwts.parser()
                            .verifyWith(properties.secretKey())
                            .build()
                            .parseSignedClaims(tokenString)
                            .getPayload();
            return new JwtRawToken(claims.getSubject(), claims.getId(), claims.getExpiration());
        } catch (ExpiredJwtException e) {
            throw new ExpiredAccessTokenException("인증 시간이 만료되었습니다. 다시 로그인 해주세요.");
        } catch (UnsupportedJwtException
                | MalformedJwtException
                | SignatureException
                | IllegalArgumentException e) {
            e.printStackTrace();
            throw new InvalidAccessTokenException("인증 요청이 잘못되었습니다. 다시 로그인 해주세요.");
        }
    }
}
