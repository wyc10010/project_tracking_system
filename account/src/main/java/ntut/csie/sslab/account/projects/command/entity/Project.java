package ntut.csie.sslab.account.projects.command.entity;

import ntut.csie.sslab.account.projects.command.entity.event.ProjectAdded;
import ntut.csie.sslab.account.projects.command.entity.event.ProjectMemberAdded;
import ntut.csie.sslab.account.projects.command.entity.event.RepositoryAdded;
import ntut.csie.sslab.ddd.model.AggregateRoot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Project extends AggregateRoot {
    private List<Repository> repositories;
    private Map<String, Role> projectMembers;

    public Project(String id, String name, String username) {
        super(id, name);
        repositories = new ArrayList<>();
        projectMembers = new HashMap<>();
        projectMembers.put(username, Role.PROJECT_MANAGER);
        addDomainEvent(new ProjectAdded(username, id, name));
    }

    public void addProjectMember(String username, Role role){
        projectMembers.put(username, role);
        addDomainEvent(new ProjectMemberAdded(username, id, role.toString()));
    }

    public void addRepository(String ownerName, String repositoryName){
        Repository repository = new Repository(ownerName, repositoryName);
        repositories.add(repository);
        addDomainEvent(new RepositoryAdded(ownerName, repositoryName, id));
    }

    public void removeProjectMember(String username){
        projectMembers.remove(username);
    }

    public void removeRepository(String ownerName, String repositoryName) {
        int removeTargetIndex = -1;
        for(Repository repository: repositories){
            if(repository.getOwnerName().equals(ownerName) && repository.getRepositoryName().equals(repositoryName))
                removeTargetIndex = repositories.indexOf(repository);
        }
        if(removeTargetIndex != -1)
            repositories.remove(removeTargetIndex);
    }
    public void changeMemberRole(String username, String role) {
        projectMembers.put(username, Role.valueOf(role));
    }

    public void setRepositories(List<Repository> repositories) {
        this.repositories = repositories;
    }

    public void setProjectMembers(Map<String, Role> projectMembers) { this.projectMembers = projectMembers; }

    public List<Repository> getRepositories() {
        return repositories;
    }

    public Map<String, Role> getProjectMembers() {
        return projectMembers;
    }



}
