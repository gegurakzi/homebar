package io.malachai.homebar.domain;

public interface TokenParser {

    RawToken parse(String tokenString);
}
