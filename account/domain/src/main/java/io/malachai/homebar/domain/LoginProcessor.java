package io.malachai.homebar.domain;

import io.malachai.homebar.domain.model.Account;
import io.malachai.homebar.domain.model.AccountRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LoginProcessor {

    private final AccountRepository repository;
    private final PasswordEncryptor encryptor;

    public Account login(String email, String password) {
        Account account = repository.findByEmail(email);
        if (account == null) throw new AccountNotFoundException("사용자를 찾을 수 없습니다.");
        if (!encryptor.matches(password, account.getPassword()))
            throw new WrongPasswordException("비밀번호가 틀렸습니다.");
        account.login();
        repository.save(account);
        return account;
    }
}
