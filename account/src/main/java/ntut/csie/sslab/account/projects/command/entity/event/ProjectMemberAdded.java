package ntut.csie.sslab.account.projects.command.entity.event;

import ntut.csie.sslab.ddd.model.DateProvider;
import ntut.csie.sslab.ddd.model.DomainEvent;

import java.util.Date;

public class ProjectMemberAdded implements DomainEvent {
    private Date occurredOn;
    private String username;
    private String projectId;
    private String role;

    public ProjectMemberAdded(String username, String projectId, String role) {
        this.occurredOn = DateProvider.now();
        this.username = username;
        this.projectId = projectId;
        this.role = role;
    }

    @Override
    public Date occurredOn() {
        return occurredOn;
    }

    public String username() {
        return username;
    }

    public String projectId() {
        return projectId;
    }

    public String role() {
        return role;
    }
}
