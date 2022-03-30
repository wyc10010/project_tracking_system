package ntut.csie.sslab.account.projects.query.usecase;

import ntut.csie.sslab.account.AbstractSpringBootJpaTest;
import ntut.csie.sslab.account.projects.command.entity.Project;
import ntut.csie.sslab.account.projects.command.entity.Repository;
import ntut.csie.sslab.account.projects.command.entity.Role;
import ntut.csie.sslab.account.projects.query.adapter.presenter.getcontent.GetDashboardContentPresenter;
import ntut.csie.sslab.account.projects.query.usecase.getcontent.GetDashboardContentInput;
import ntut.csie.sslab.account.projects.query.usecase.getcontent.GetDashboardContentOutput;
import ntut.csie.sslab.account.projects.query.usecase.getcontent.GetDashboardContentUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@Transactional
public class GetDashboardContentUseCaseTest extends AbstractSpringBootJpaTest {

    @BeforeEach
    public void setUp(){
        super.setUp();
        super.makeSomeProjectDataInTheDB();
    }

    @Test
    public void should_success_when_get_dashboard_content(){
        GetDashboardContentUseCase useCase = newGetDashboardContentUseCase();
        GetDashboardContentInput input = useCase.newInput();
        input.setUsername(username);
        GetDashboardContentOutput output = new GetDashboardContentPresenter();
        useCase.execute(input, output);

        assertEquals(2, output.getProjects().size());

        Project project1 = output.getProjects().stream().filter(project -> project.getId().equals(project1Id)).findFirst().get();
        Project project3 = output.getProjects().stream().filter(project -> project.getId().equals(project3Id)).findFirst().get();
        assertEquals(2, project1.getRepositories().size());
        List<Repository> repositories = project1.getRepositories();
        assertTrue(repositories.stream().anyMatch(repository ->
                repository.getOwnerName().equals(ownerName1) && repository.getRepositoryName().equals(repositoryName1)));
        assertTrue(repositories.stream().anyMatch(repository ->
                repository.getOwnerName().equals(ownerName2) && repository.getRepositoryName().equals(repositoryName2)));
        assertTrue(project1.getProjectMembers().containsKey(username));
        assertEquals(Role.PROJECT_MANAGER, project1.getProjectMembers().get(username));
        assertTrue(project1.getProjectMembers().containsKey(anotherUser));
        assertEquals(Role.PROJECT_USER, project1.getProjectMembers().get(anotherUser));

        repositories = project3.getRepositories();
        assertTrue(repositories.stream().anyMatch(repository ->
                repository.getOwnerName().equals(ownerName4) && repository.getRepositoryName().equals(repositoryName4)));
        assertTrue(project3.getProjectMembers().containsKey(username));
        assertEquals(Role.PROJECT_USER, project3.getProjectMembers().get(username));
        assertTrue(project3.getProjectMembers().containsKey(anotherUser));
        assertEquals(Role.PROJECT_MANAGER, project3.getProjectMembers().get(anotherUser));
    }
}
