package io.malachai.homebar.extern.jwt;

import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import javax.crypto.SecretKey;

public class JwtTokenProperties {

    private static final String secretKey = "d3fe9efe-d30d-4239-b9f2-542afc2b9608";
    private static final Long accessTokenExpirationMillis = 3_600_000L;
    private static final Long refreshTokenExpirationMillis = 1_209_600_000L;

    public SecretKey secretKey() {
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public Long accessTokenExpirationMillis() {
        return accessTokenExpirationMillis;
    }

    public Long refreshTokenExpirationMillis() {
        return refreshTokenExpirationMillis;
    }
}
