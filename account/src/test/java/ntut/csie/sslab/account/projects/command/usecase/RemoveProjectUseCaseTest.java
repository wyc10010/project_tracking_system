package ntut.csie.sslab.account.projects.command.usecase;

import ntut.csie.sslab.account.AbstractSpringBootJpaTest;
import ntut.csie.sslab.account.projects.command.usecase.removeproject.RemoveProjectInput;
import ntut.csie.sslab.account.projects.command.usecase.removeproject.RemoveProjectUseCase;
import ntut.csie.sslab.ddd.adapter.presenter.cqrs.CqrsCommandPresenter;
import ntut.csie.sslab.ddd.usecase.cqrs.CqrsCommandOutput;
import ntut.csie.sslab.ddd.usecase.cqrs.ExitCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class RemoveProjectUseCaseTest extends AbstractSpringBootJpaTest {
    @BeforeEach
    public void setUp(){
        super.setUp();
        registerUser(username);
        projectId = addProject(username, projectName).getId();
    }
    @Test
    public void should_success_when_remove_project(){
        RemoveProjectUseCase useCase = newRemoveProjectUseCase();
        RemoveProjectInput input = useCase.newInput();
        CqrsCommandOutput output = CqrsCommandPresenter.newInstance();
        input.setProjectId(projectId);
        useCase.execute(input, output);

        assertEquals(projectId, output.getId());
        assertNull(projectRepository.findById(output.getId()).orElse(null));
    }
    @Test
    public void should_fail_when_project_not_exist(){
        String projectNotExistingId = "notExisting";
        CqrsCommandOutput output = removeProject(projectNotExistingId);
        assertEquals("The project does not exist.", output.getMessage());
        assertEquals(ExitCode.FAILURE, output.getExitCode());
    }
}
