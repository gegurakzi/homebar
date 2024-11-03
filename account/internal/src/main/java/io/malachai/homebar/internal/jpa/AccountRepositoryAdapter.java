package io.malachai.homebar.internal.jpa;

import io.malachai.homebar.domain.model.Account;
import io.malachai.homebar.domain.model.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AccountRepositoryAdapter implements AccountRepository {

    private final JpaAccountInterface jpaInterface;

    @Override
    public void save(Account account) {
        jpaInterface.save(account);
    }

    public Account findById(Long id) {
        return jpaInterface.findById(id).orElseThrow(/*EXCEPTION*/ );
    }

    @Override
    public Account findByEmail(String email) {
        return jpaInterface.findByEmail(email);
    }
}
