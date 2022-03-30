package ntut.csie.sslab.account.projects.command.entity.event;

import ntut.csie.sslab.ddd.model.DateProvider;
import ntut.csie.sslab.ddd.model.DomainEvent;

import java.util.Date;

public class ProjectAdded implements DomainEvent {
    private Date occurredOn;
    private String userId;
    private String projectId;
    private String projectName;
    public ProjectAdded(String userId, String id, String name) {
        this.occurredOn = DateProvider.now();
        this.userId = userId;
        this.projectId = id;
        this.projectName = name;
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

    public String projectName() {
        return projectName;
    }
}
