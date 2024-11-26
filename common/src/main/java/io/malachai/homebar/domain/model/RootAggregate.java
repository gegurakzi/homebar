package io.malachai.homebar.domain.model;

import io.malachai.homebar.domain.event.DomainEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

public abstract class RootAggregate {

    private Queue<DomainEvent> events = new LinkedList<>();

    public <T extends DomainEvent> void offerEvent(T event) {
        this.events.offer(event);
    }

    public Optional<DomainEvent> pollEvent() {
        return Optional.ofNullable(this.events.poll());
    }

    public List<DomainEvent> pollAllEvents() {
        List<DomainEvent> eventList = new ArrayList<>();
        if (this.events.isEmpty()) return eventList;
        while (!events.isEmpty()) {
            eventList.add(this.events.poll());
        }
        return eventList;
    }
}
