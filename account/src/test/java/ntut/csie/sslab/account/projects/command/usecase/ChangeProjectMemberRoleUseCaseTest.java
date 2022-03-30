package ntut.csie.sslab.account.projects.command.usecase;

import ntut.csie.sslab.account.AbstractSpringBootJpaTest;
import ntut.csie.sslab.account.projects.command.entity.Project;
import ntut.csie.sslab.account.projects.command.entity.Role;
import ntut.csie.sslab.account.projects.command.usecase.changememberrole.ChangeProjectMemberRoleInput;
import ntut.csie.sslab.account.projects.command.usecase.changememberrole.ChangeProjectMemberRoleUseCase;
import ntut.csie.sslab.ddd.adapter.presenter.cqrs.CqrsCommandPresenter;
import ntut.csie.sslab.ddd.usecase.cqrs.CqrsCommandOutput;
import ntut.csie.sslab.ddd.usecase.cqrs.ExitCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ChangeProjectMemberRoleUseCaseTest extends AbstractSpringBootJpaTest {

    @BeforeEach
    public void setUp(){
        super.setUp();
        makeSomeProjectDataInTheDB();
    }
    @Test
    public void should_success_when_change_role(){
        ChangeProjectMemberRoleUseCase changeProjectMemberRoleUseCase = newChangeProjectMemberRoleUseCase();
        ChangeProjectMemberRoleInput input = changeProjectMemberRoleUseCase.newInput();
        CqrsCommandOutput output = CqrsCommandPresenter.newInstance();
        input.setProjectId(project1Id);
        input.setUsername(anotherUser);
        input.setRole(Role.PROJECT_MANAGER.toString());
        changeProjectMemberRoleUseCase.execute(input, output);

        Project project = projectRepository.findById(project1Id).get();
        assertEquals(Role.PROJECT_MANAGER, project.getProjectMembers().get(anotherUser));
    }
    @Test
    public void should_fail_when_project_does_not_exist(){
        CqrsCommandOutput output = changeMemberRole(projectId, username, Role.PROJECT_MANAGER.toString());
        assertEquals(ExitCode.FAILURE, output.getExitCode());
        assertEquals("The project does not exist.", output.getMessage());
    }

    @Test
    public void should_fail_when_user_does_not_exit(){
        CqrsCommandOutput output = changeMemberRole(project1Id, "notExistUser", Role.PROJECT_MANAGER.toString());
        assertEquals(ExitCode.FAILURE, output.getExitCode());
        assertEquals("The user is not in the project member list.", output.getMessage());
    }
    @Test
    public void should_fail_when_there_is_no_manager_left_after_change(){
        CqrsCommandOutput output = changeMemberRole(project1Id, username, Role.PROJECT_USER.toString());
        assertEquals(ExitCode.FAILURE, output.getExitCode());
        assertEquals("There should be at least one manager in the project member list, and your operation would cause no manager in the project member list. Please add a new manager before the operation.", output.getMessage());
    }
}
