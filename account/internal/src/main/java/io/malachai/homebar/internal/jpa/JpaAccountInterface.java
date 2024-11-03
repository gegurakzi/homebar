package io.malachai.homebar.internal.jpa;

import io.malachai.homebar.domain.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaAccountInterface extends JpaRepository<Account, Long> {

    Account findByEmail(String email);
}
