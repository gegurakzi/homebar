package io.malachai.homebar.domain;

import static org.mockito.Mockito.*;

import io.malachai.homebar.domain.model.Account;

public class AccountFixture {

    public static Account a() {
        Long id = 1L;
        String email = "a@test.com";
        String password = "raw-password";
        String nickname = "iamtest";
        String emailVerifyToken = "random-token";
        boolean isEmailVerified = false;

        return new Account(
                id,
                email,
                password,
                nickname,
                emailVerifyToken,
                isEmailVerified,
                null,
                null,
                null,
                null);
    }
}
