package ntut.csie.sslab.account.projects.command.usecase;

import ntut.csie.sslab.account.AbstractSpringBootJpaTest;
import ntut.csie.sslab.account.projects.command.entity.Project;
import ntut.csie.sslab.account.projects.command.usecase.removerepository.RemoveRepositoryInput;
import ntut.csie.sslab.account.projects.command.usecase.removerepository.RemoveRepositoryUseCase;
import ntut.csie.sslab.ddd.adapter.presenter.cqrs.CqrsCommandPresenter;
import ntut.csie.sslab.ddd.usecase.cqrs.CqrsCommandOutput;
import ntut.csie.sslab.ddd.usecase.cqrs.ExitCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;

@Transactional
public class RemoveRepositoryUseCaseTest extends AbstractSpringBootJpaTest {
    @BeforeEach
    public void setUp(){
        super.setUp();
        registerUser(username);
        projectId = addProject(username, projectName).getId();
        addRepository(projectId, ownerName, repositoryName);
    }
    @Test
    public void should_success_when_remove_repository(){
        RemoveRepositoryUseCase useCase = newRemoveRepositoryUseCase();
        RemoveRepositoryInput input = useCase.newInput();
        CqrsCommandOutput output = CqrsCommandPresenter.newInstance();
        input.setProjectId(projectId);
        input.setOwnerName(ownerName);
        input.setRepositoryName(repositoryName);
        useCase.execute(input, output);

        Project project = projectRepository.findById(output.getId()).get();
        assertEquals(0, project.getRepositories().size());

    }
    @Test
    public void should_fail_when_project_does_not_exist(){
        String nonExistingProjectId = "nonExistingProjectId";
        CqrsCommandOutput output = removeRepository(nonExistingProjectId, ownerName, repositoryName);
        assertEquals("The project does not exist.", output.getMessage());
        assertEquals(ExitCode.FAILURE, output.getExitCode());
    }
    @Test
    public void should_fail_when_repository_does_not_exist(){
        CqrsCommandOutput output = removeRepository(projectId, "nonExistOwnerName", "nonExistRepositoryName");
        assertEquals("The repository does not exist in the project.", output.getMessage());
        assertEquals(ExitCode.FAILURE, output.getExitCode());

    }
}
