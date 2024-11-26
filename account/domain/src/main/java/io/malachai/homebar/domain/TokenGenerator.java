package io.malachai.homebar.domain;

public interface TokenGenerator {

    TokenPair generate(AuthenticatedUser principal);
}
