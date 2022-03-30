package ntut.csie.sslab.account.projects.command.entity.event;

import ntut.csie.sslab.ddd.model.DateProvider;
import ntut.csie.sslab.ddd.model.DomainEvent;

import java.util.Date;

public class RepositoryAdded implements DomainEvent {
    private String ownerName;
    private String repositoryName;
    private String projectId;
    private Date occurredOn;

    public RepositoryAdded(String ownerName, String repositoryName, String projectId) {
        this.ownerName = ownerName;
        this.repositoryName = repositoryName;
        this.projectId = projectId;
        this.occurredOn = DateProvider.now();
    }

    @Override
    public Date occurredOn() {
        return occurredOn;
    }

    public String ownerName() {
        return ownerName;
    }

    public String repositoryName() {
        return repositoryName;
    }

    public String projectId() {
        return projectId;
    }
}
