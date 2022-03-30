package ntut.csie.sslab.account.projects.command.entity;

import ntut.csie.sslab.account.AbstractSpringBootJpaTest;
import ntut.csie.sslab.account.projects.command.entity.event.ProjectAdded;
import ntut.csie.sslab.account.projects.command.entity.event.ProjectMemberAdded;
import ntut.csie.sslab.account.projects.command.entity.event.RepositoryAdded;
import ntut.csie.sslab.ddd.model.DomainEvent;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ProjectTest extends AbstractSpringBootJpaTest {

    @Test
    public void add_project_should_publish_project_added_domain_event(){
        Project project = new Project(projectId, projectName, username);
        assertEquals(projectId, project.getId());
        assertEquals(projectName, project.getName());

        Map<String, Role> projectMember = project.getProjectMembers();
        assertEquals(1, projectMember.size());
        assertTrue(projectMember.containsKey(username));
        assertEquals(Role.PROJECT_MANAGER, projectMember.get(username));
        assertEquals(0, project.getRepositories().size());


        assertEquals(1, project.getDomainEvents().size());
        ProjectAdded event = (ProjectAdded)project.getDomainEvents().get(0);
        assertEquals(username, event.userId());
        assertEquals(projectId, event.projectId());
        assertEquals(projectName, event.projectName());
    }

    @Test
    public void add_project_member_should_publish_project_member_added_domain_event(){
        String anotherUsername = "anotherUser";
        Project project = new Project(projectId, projectName, username);
        project.clearDomainEvents();
        project.addProjectMember(anotherUsername, Role.PROJECT_USER);
        Map<String, Role> projectMember = project.getProjectMembers();
        assertEquals(2, projectMember.size());
        assertTrue(projectMember.containsKey(anotherUsername));
        assertEquals(Role.PROJECT_USER, projectMember.get(anotherUsername));

        assertEquals(1, project.getDomainEvents().size());
        ProjectMemberAdded event = (ProjectMemberAdded)project.getDomainEvents().get(0);
        assertEquals(anotherUsername, event.username());
        assertEquals(Role.PROJECT_USER.toString(), event.role());
        assertEquals(projectId, event.projectId());
    }

    @Test
    public void add_repository_should_publish_repository_added_domain_event(){
        Project project = new Project(projectId, projectName, username);
        project.clearDomainEvents();
        project.addRepository(ownerName, repositoryName);
        assertEquals(1, project.getRepositories().size());

        Repository repository = project.getRepositories().get(0);
        assertEquals(ownerName, repository.getOwnerName());
        assertEquals(repositoryName, repository.getRepositoryName());

        RepositoryAdded event = (RepositoryAdded)project.getDomainEvents().get(0);
        assertEquals(ownerName, event.ownerName());
        assertEquals(repositoryName, event.repositoryName());
        assertEquals(project.getId(), event.projectId());
    }

    @Test//no domain event here
    public void change_member_role_should_publish_member_role_changed_domain_event(){
        Project project = new Project(projectId, projectName, username);
        assertEquals(Role.PROJECT_MANAGER, project.getProjectMembers().get(username));
        project.clearDomainEvents();
        project.changeMemberRole(username, Role.PROJECT_USER.toString());
        assertEquals(Role.PROJECT_USER, project.getProjectMembers().get(username));
    }

    @Test//no domain event here
    public void remove_repository_should_publish_repository_removed_domain_event(){
        Project project = new Project(projectId, projectName, username);
        project.addRepository(ownerName, repositoryName);
        project.removeRepository(ownerName, repositoryName);
        assertEquals(0, project.getRepositories().size());
    }
}
