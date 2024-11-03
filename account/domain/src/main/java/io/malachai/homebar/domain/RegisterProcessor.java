package io.malachai.homebar.domain;

import io.malachai.homebar.domain.model.Account;
import io.malachai.homebar.domain.model.AccountRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RegisterProcessor {

    private final AccountRepository repository;
    private final PasswordEncryptor encryptor;

    public Account register(String email, String password, String nickname) {
        Account account = Account.register(email, encryptor.encrypt(password), nickname);
        account.verifyEmail();
        repository.save(account);
        return account;
    }

    public Account confirmRegister(String email, String token) {
        Account account = repository.findByEmail(email);
        if (account.getEmailVerifyToken().equals(token)) {
            account.confirmRegister();
        }
        repository.save(account);
        return account;
    }
}