package ntut.csie.sslab.account.projects.command.usecase;

import ntut.csie.sslab.account.AbstractSpringBootJpaTest;
import ntut.csie.sslab.account.projects.command.entity.Project;
import ntut.csie.sslab.account.projects.command.entity.Role;
import ntut.csie.sslab.account.projects.command.usecase.addmember.AddProjectMemberInput;
import ntut.csie.sslab.account.projects.command.usecase.addmember.AddProjectMemberUseCase;
import ntut.csie.sslab.ddd.adapter.presenter.cqrs.CqrsCommandPresenter;
import ntut.csie.sslab.ddd.usecase.cqrs.CqrsCommandOutput;
import ntut.csie.sslab.ddd.usecase.cqrs.ExitCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@Transactional
public class AddProjectMemberUseCaseTest extends AbstractSpringBootJpaTest {
    private String projectId;
    private String anotherUser = "anotherUser";

    @BeforeEach
    public void setUp(){
        super.setUp();
        registerUser(username);
        registerUser(anotherUser);
        projectId = addProject(username, projectName).getId();
    }

    @Test
    public void should_success_when_add_project_member(){
        AddProjectMemberUseCase useCase = newAddProjectMemberUseCase();
        AddProjectMemberInput input = useCase.newInput();
        CqrsCommandOutput output = CqrsCommandPresenter.newInstance();
        input.setProjectId(projectId);
        input.setUsername(anotherUser);
        input.setRole(Role.PROJECT_USER.toString());
        useCase.execute(input, output);
        assertEquals(ExitCode.SUCCESS, output.getExitCode());
        assertEquals(projectId, output.getId());

        Project project = projectRepository.findById(projectId).get();
        assertEquals(2, project.getProjectMembers().size());

        Map<String, Role> projectMembers = project.getProjectMembers();
        assertTrue(projectMembers.containsKey(username));
        assertEquals(Role.PROJECT_MANAGER, projectMembers.get(username));
        assertTrue(projectMembers.containsKey(anotherUser));
        assertEquals(Role.PROJECT_USER, projectMembers.get(anotherUser));

    }

    @Test
    public void should_fail_when_member_already_in_list(){
        addProjectMemeber(projectId, username, Role.PROJECT_USER.toString());
        CqrsCommandOutput output = addProjectMemeber(projectId, username, Role.PROJECT_USER.toString());
        assertEquals(ExitCode.FAILURE, output.getExitCode());
        assertEquals("The user is already in the list if the project.", output.getMessage());
    }

    @Test
    public void should_fail_when_member_is_not_a_user_in_system(){
        String notRegisterUser = "notRegisterUser";
        CqrsCommandOutput output = addProjectMemeber(projectId, notRegisterUser, Role.PROJECT_USER.toString());
        assertEquals(ExitCode.FAILURE, output.getExitCode());
        assertEquals("The user does not exist in the system.", output.getMessage());
    }

    @Test
    public void should_fail_when_project_does_not_exist(){
        String projectNotExistingId = "projectNotExistingId";
        CqrsCommandOutput output = addProjectMemeber(projectNotExistingId, anotherUser, Role.PROJECT_USER.toString());
        assertEquals(ExitCode.FAILURE, output.getExitCode());
        assertEquals("The project does not exist in the system.", output.getMessage());
    }

}
