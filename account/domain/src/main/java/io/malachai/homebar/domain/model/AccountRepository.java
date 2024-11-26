package io.malachai.homebar.domain.model;

public interface AccountRepository {

    void save(Account account);

    Account findById(Long id);

    Account findByEmail(String email);
}
