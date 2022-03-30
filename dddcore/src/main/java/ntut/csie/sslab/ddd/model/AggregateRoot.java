package ntut.csie.sslab.ddd.model;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class AggregateRoot extends Entity {

    private final List<DomainEvent> domainEvents;

    public AggregateRoot(String id, String name) {
        super(id, name);
        this.domainEvents = new CopyOnWriteArrayList<>();
    }

    public void addDomainEvent(DomainEvent domainEvent) {
        domainEvents.add(domainEvent);
    }

    public List<DomainEvent> getDomainEvents() {
        return Collections.unmodifiableList(domainEvents);
    }

    public void clearDomainEvents(){
        domainEvents.clear();
    }
}
