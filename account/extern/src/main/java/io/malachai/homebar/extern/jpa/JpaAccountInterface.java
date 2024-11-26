package io.malachai.homebar.extern.jpa;

import io.malachai.homebar.domain.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaAccountInterface extends JpaRepository<Account, Long> {

    Account findByEmail(String email);
}
