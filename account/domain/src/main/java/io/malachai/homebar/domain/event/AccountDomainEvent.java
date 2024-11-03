package io.malachai.homebar.domain.event;

import java.util.Date;

public abstract class AccountDomainEvent implements DomainEvent {

    private final Date occuredOn;
    private final String email;

    public AccountDomainEvent(String email) {
        this.email = email;
        this.occuredOn = new Date();
    }

    public String accountEmail() {
        return this.email;
    }

    @Override
    public Date occuredOn() {
        return this.occuredOn;
    }
}
