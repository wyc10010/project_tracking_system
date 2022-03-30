package ntut.csie.sslab.account.users.command.entity;

import ntut.csie.sslab.account.users.command.entity.event.UserRegistered;
import ntut.csie.sslab.ddd.model.AggregateRoot;

public class User extends AggregateRoot {

    public User(String name) {
        super("", name);
        addDomainEvent(new UserRegistered(getName()));
    }
}
