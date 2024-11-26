package io.malachai.homebar.domain;

import java.util.Date;

public record RawToken(
        String subject, AuthenticatedUser principal, String tokenId, Date expiration) {}
