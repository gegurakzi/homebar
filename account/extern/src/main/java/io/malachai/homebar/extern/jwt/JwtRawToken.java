package io.malachai.homebar.extern.jwt;

import java.util.Date;

public record JwtRawToken(String subject, String tokenId, Date expiration) {}
