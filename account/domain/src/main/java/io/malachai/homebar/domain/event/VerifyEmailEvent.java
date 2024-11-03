package io.malachai.homebar.domain.event;

public class VerifyEmailEvent extends AccountDomainEvent {

    public VerifyEmailEvent(String email) {
        super(email);
    }
}
