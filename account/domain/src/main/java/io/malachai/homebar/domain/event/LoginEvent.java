package io.malachai.homebar.domain.event;

public class LoginEvent extends AccountDomainEvent {

    public LoginEvent(String email) {
        super(email);
    }
}
