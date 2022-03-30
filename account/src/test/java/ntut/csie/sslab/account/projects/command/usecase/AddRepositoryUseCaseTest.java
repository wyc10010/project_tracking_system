package ntut.csie.sslab.account.projects.command.usecase;

import ntut.csie.sslab.account.AbstractSpringBootJpaTest;
import ntut.csie.sslab.account.projects.command.entity.Project;
import ntut.csie.sslab.account.projects.command.entity.Repository;
import ntut.csie.sslab.account.projects.command.usecase.addrepository.AddRepositoryInput;
import ntut.csie.sslab.account.projects.command.usecase.addrepository.AddRepositoryUseCase;
import ntut.csie.sslab.ddd.adapter.presenter.cqrs.CqrsCommandPresenter;
import ntut.csie.sslab.ddd.usecase.cqrs.CqrsCommandOutput;
import ntut.csie.sslab.ddd.usecase.cqrs.ExitCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@Transactional
public class AddRepositoryUseCaseTest extends AbstractSpringBootJpaTest {
    @BeforeEach
    public void setUp(){
        super.setUp();
    }

    @Test
    public void should_success_when_add_repository(){
        String projectId = addProject(username, projectName).getId();

        AddRepositoryUseCase useCase = newAddRepositoryUseCase();
        AddRepositoryInput input = useCase.newInput();
        input.setProjectId(projectId);
        input.setOwnerName(ownerName);
        input.setRepositoryName(repositoryName);
        CqrsCommandOutput output = CqrsCommandPresenter.newInstance();
        useCase.execute(input, output);

        assertNotNull(output.getId());
        assertEquals(ExitCode.SUCCESS, output.getExitCode());

        Project project = projectRepository.findById(projectId).get();
        Repository repository = project.getRepositories().get(0);

        assertEquals(1, project.getRepositories().size());
        assertEquals(ownerName, repository.getOwnerName());
        assertEquals(repositoryName, repository.getRepositoryName());
    }
    @Test
    public void should_fail_when_add_repository_to_a_not_existing_project(){
        CqrsCommandOutput output = addRepository(projectId, ownerName, repositoryName);
        assertEquals(ExitCode.FAILURE, output.getExitCode());
        assertEquals("The project does not exist in the system.", output.getMessage());
    }
}
