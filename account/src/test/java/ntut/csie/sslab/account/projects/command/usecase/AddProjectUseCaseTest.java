package ntut.csie.sslab.account.projects.command.usecase;

import ntut.csie.sslab.account.AbstractSpringBootJpaTest;
import ntut.csie.sslab.account.projects.command.entity.Project;
import ntut.csie.sslab.account.projects.command.entity.Role;
import ntut.csie.sslab.account.projects.command.usecase.add.AddProjectInput;
import ntut.csie.sslab.account.projects.command.usecase.add.AddProjectUseCase;
import ntut.csie.sslab.ddd.adapter.presenter.cqrs.CqrsCommandPresenter;
import ntut.csie.sslab.ddd.usecase.cqrs.CqrsCommandOutput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@Transactional
public class AddProjectUseCaseTest extends AbstractSpringBootJpaTest {
    @BeforeEach
    public void setUp(){
        super.setUp();
    }
    @Test
    public void should_success_when_add_project(){
        registerUser(username);

        AddProjectUseCase useCase = newAddProjectUseCase();
        AddProjectInput input =  useCase.newInput();
        CqrsCommandOutput output = CqrsCommandPresenter.newInstance();
        input.setUsername(username);
        input.setProjectName(projectName);
        useCase.execute(input, output);

        Project project = projectRepository.findById(output.getId()).get();
        assertEquals(output.getId(), project.getId());
        assertEquals(projectName, project.getName());
        assertEquals(0, project.getRepositories().size());

        Map<String, Role> projectMembers = project.getProjectMembers();
        assertEquals(1, projectMembers.size());
        assertTrue(projectMembers.containsKey(username));
        assertEquals(Role.PROJECT_MANAGER, projectMembers.get(username));
    }
}
