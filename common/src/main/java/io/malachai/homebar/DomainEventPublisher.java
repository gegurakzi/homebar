package io.malachai.homebar;

import io.malachai.homebar.domain.event.DomainEvent;

public interface DomainEventPublisher {

    void publishEvent(DomainEvent e);
}
