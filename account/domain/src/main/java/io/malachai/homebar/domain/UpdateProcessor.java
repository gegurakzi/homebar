package io.malachai.homebar.domain;

import io.malachai.homebar.domain.model.Account;
import io.malachai.homebar.domain.model.AccountRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdateProcessor {

    private final AccountRepository repository;

    public Account update(String email, String nickname) {
        Account account = repository.findByEmail(email);
        account.update(email);
        repository.save(account);
        return account;
    }
}
