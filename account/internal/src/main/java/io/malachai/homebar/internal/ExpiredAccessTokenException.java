package io.malachai.homebar.internal;

public class ExpiredAccessTokenException extends RuntimeException {

    public ExpiredAccessTokenException(String message) {
        super(message);
    }
}
