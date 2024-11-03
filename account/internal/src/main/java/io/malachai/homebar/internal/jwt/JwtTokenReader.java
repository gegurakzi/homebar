package io.malachai.homebar.internal.jwt;

import java.util.Optional;

public class JwtTokenReader {

    private static final String HEADER_PREFIX = "Bearer ";

    public Optional<String> read(String header) {
        if (header == null || header.isBlank()) {
            return Optional.empty();
        } else {
            if (header.length() < HEADER_PREFIX.length()) {
                return Optional.empty();
            } else {
                return Optional.of(header.substring(HEADER_PREFIX.length()));
            }
        }
    }
}
