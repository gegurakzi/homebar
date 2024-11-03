package io.malachai.homebar.domain.event;

public class RegisterConfirmedEvent extends AccountDomainEvent {

    public RegisterConfirmedEvent(String email) {
        super(email);
    }
}
