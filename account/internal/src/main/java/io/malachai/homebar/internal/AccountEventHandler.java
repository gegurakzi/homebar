package io.malachai.homebar.internal;

import io.malachai.homebar.domain.event.AccountDomainEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountEventHandler {

    private final ApplicationEventPublisher publisher;

    @EventListener
    public void handle(AccountDomainEvent e) {}
}
