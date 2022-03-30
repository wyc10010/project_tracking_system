package ntut.csie.sslab.account.users.command.entity.event;

import ntut.csie.sslab.ddd.model.DateProvider;
import ntut.csie.sslab.ddd.model.DomainEvent;

import java.util.Date;

public class UserRegistered implements DomainEvent {
    private Date occurredOn;
    private String username;

    public UserRegistered(String username) {
        occurredOn = DateProvider.now();
        this.username = username;
    }

    @Override
    public Date occurredOn() {
        return occurredOn;
    }

    public String username() {
        return username;
    }
}
