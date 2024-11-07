package io.malachai.homebar.extern.jwt;

import io.jsonwebtoken.SignatureAlgorithm;

public class JwtTokenProperties {

    private static final String secretKey = "76aab302";
    private static final Long accessTokenExpirationMillis = 3_600_000L;
    private static final Long refreshTokenExpirationMillis = 1_209_600_000L;
    private static final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    public String secretKey() {
        return this.secretKey;
    }

    public Long accessTokenExpirationMillis() {
        return this.accessTokenExpirationMillis;
    }

    public Long refreshTokenExpirationMillis() {
        return this.refreshTokenExpirationMillis;
    }

    public SignatureAlgorithm signatureAlgorithm() {
        return this.signatureAlgorithm;
    }
}
