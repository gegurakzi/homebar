package io.malachai.homebar.extern;

public class ExpiredAccessTokenException extends RuntimeException {

    public ExpiredAccessTokenException(String message) {
        super(message);
    }
}
