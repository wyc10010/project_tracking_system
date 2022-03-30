package ntut.csie.sslab.account.projects.command.usecase;

import ntut.csie.sslab.account.AbstractSpringBootJpaTest;
import ntut.csie.sslab.account.projects.command.entity.Project;
import ntut.csie.sslab.account.projects.command.entity.Role;
import ntut.csie.sslab.account.projects.command.usecase.removemember.RemoveProjectMemberInput;
import ntut.csie.sslab.account.projects.command.usecase.removemember.RemoveProjectMemberUseCase;
import ntut.csie.sslab.ddd.adapter.presenter.cqrs.CqrsCommandPresenter;
import ntut.csie.sslab.ddd.usecase.cqrs.CqrsCommandOutput;
import ntut.csie.sslab.ddd.usecase.cqrs.ExitCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;
@Transactional
public class RemoveProjectMemberUseCaseTest extends AbstractSpringBootJpaTest {

    @BeforeEach
    public void setUp(){
        super.setUp();
        projectId = addProject(username, projectName).getId();
        registerUser(username);
        registerUser(anotherUser);

    }
    @Test
    public void should_success_when_remove_project_member(){
        addProjectMemeber(projectId, anotherUser, Role.PROJECT_USER.toString());
        RemoveProjectMemberUseCase useCase = newRemoveProjectMemberUseCase();
        RemoveProjectMemberInput input = useCase.newInput();
        CqrsCommandOutput output = CqrsCommandPresenter.newInstance();
        input.setProjectId(projectId);
        input.setUsername(anotherUser);
        useCase.execute(input, output);
        Project project = projectRepository.findById(output.getId()).get();
        assertEquals(projectId, output.getId());
        assertEquals(ExitCode.SUCCESS, output.getExitCode());
        assertFalse(project.getProjectMembers().containsKey(anotherUser));
    }
    @Test
    public void should_fail_when_project_does_not_exist(){
        String nonExistingProjecId = "nonExistingProjecId";
        CqrsCommandOutput output = removeProjectMember(nonExistingProjecId, anotherUser);
        assertEquals(ExitCode.FAILURE, output.getExitCode());
        assertEquals("The project does not exist.", output.getMessage());
    }
    @Test
    public void should_fail_when_user_not_in_the_project_member_list(){
        CqrsCommandOutput output = removeProjectMember(projectId, anotherUser);
        assertEquals(ExitCode.FAILURE, output.getExitCode());
        assertEquals("The member is not in the member list.", output.getMessage());
    }

    @Test
    public void should_fail_when_no_manager_in_the_project_member_list_after_operation(){
        CqrsCommandOutput output = removeProjectMember(projectId, username);
        assertEquals(ExitCode.FAILURE, output.getExitCode());
        assertEquals("There would be no manager left in the member list. Please add a manager before the operation.", output.getMessage());
    }
}

