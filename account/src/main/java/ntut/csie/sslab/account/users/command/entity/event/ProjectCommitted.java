package ntut.csie.sslab.account.users.command.entity.event;

import ntut.csie.sslab.ddd.model.DateProvider;
import ntut.csie.sslab.ddd.model.DomainEvent;

import java.util.Date;

public class ProjectCommitted implements DomainEvent {
    private Date occurredOn;
    private String userId;
    private String projectId;
    private int order;
    public ProjectCommitted(String id, String projectId, int order) {
        occurredOn = DateProvider.now();
        this.userId = id;
        this.projectId = projectId;
        this.order = order;
    }

    @Override
    public Date occurredOn() {
        return occurredOn;
    }

    public String userId() {
        return userId;
    }

    public String projectId() {
        return projectId;
    }

    public int order() {
        return order;
    }
}
