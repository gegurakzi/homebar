package io.malachai.homebar.internal;

import io.malachai.homebar.DomainEventPublisher;
import io.malachai.homebar.domain.event.DomainEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;

@RequiredArgsConstructor
public class ApplicationDomainEventPublisher implements DomainEventPublisher {

    private final ApplicationEventPublisher publisher;

    @Override
    public void publishEvent(DomainEvent event) {
        publisher.publishEvent(event);
    }
}
