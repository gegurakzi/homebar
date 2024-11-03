package io.malachai.homebar.internal.jwt;

import java.util.Date;

public record JwtRawToken(String subject, String tokenId, Date expiration) {}
