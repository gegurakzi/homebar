package io.malachai.homebar.domain.event;

public class UpdateEvent extends AccountDomainEvent {

    public UpdateEvent(String email) {
        super(email);
    }
}
