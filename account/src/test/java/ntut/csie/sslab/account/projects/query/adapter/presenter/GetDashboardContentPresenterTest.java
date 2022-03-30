package ntut.csie.sslab.account.projects.query.adapter.presenter;

import ntut.csie.sslab.account.AbstractSpringBootJpaTest;
import ntut.csie.sslab.account.projects.command.entity.Project;
import ntut.csie.sslab.account.projects.command.entity.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class GetDashboardContentPresenterTest extends AbstractSpringBootJpaTest {

    @BeforeEach
    public void setUp(){
        super.setUp();
        super.makeSomeProjectDataInTheDB();
    }

    @Test
    public void should_success_when_sort_repoDto_by_owner_name_and_repo_name(){
        Project project = new Project(projectId, projectName, username);
        project.addRepository("Ann","SE_project");
        project.addRepository("Leo","OperatingSystem");
        project.addRepository("Gene","Dctrack_project");
        project.addRepository("Ann","Kanban_project");
        project.addRepository("Leo","AngularJs");

        List<RepositoryDto> repositoryDtos = DomainToDtoMapper.sortRepoDtoByOwnerNameAndRepoName(project.getRepositories());
        assertEquals("Kanban_project", repositoryDtos.get(0).getRepositoryName());
        assertEquals("SE_project", repositoryDtos.get(1).getRepositoryName());
        assertEquals("Dctrack_project", repositoryDtos.get(2).getRepositoryName());
        assertEquals("AngularJs", repositoryDtos.get(3).getRepositoryName());
        assertEquals("OperatingSystem", repositoryDtos.get(4).getRepositoryName());
    }

    @Test
    public void should_success_when_sort_memberDto_by_role_and_username(){
        Project project = new Project(projectId, projectName, "Edward");
        project.addProjectMember("Leo", Role.PROJECT_MANAGER);
        project.addProjectMember("Gene", Role.PROJECT_USER);
        project.addProjectMember("Ann", Role.PROJECT_MANAGER);
        project.addProjectMember("Bing", Role.PROJECT_USER);
        project.addProjectMember("Allen", Role.PROJECT_USER);

        List<MemberDto> memberDtos = DomainToDtoMapper.sortMemberDtoByRoleAndusername(project.getProjectMembers());
        assertEquals("Ann", memberDtos.get(0).getUsername());
        assertEquals("Edward", memberDtos.get(1).getUsername());
        assertEquals("Leo", memberDtos.get(2).getUsername());
        assertEquals("Allen", memberDtos.get(3).getUsername());
        assertEquals("Bing", memberDtos.get(4).getUsername());
        assertEquals("Gene", memberDtos.get(5).getUsername());
    }
//    @Test
//    public void should_build_up_view_model_when_presenter_build_view_model(){
//        GetDashboardContentPresenter presenter= getDashBoardContent(username);
//        GetDashboardContentViewModel viewModel = presenter.buildViewModel();
//        assertEquals(2, viewModel.getProjectDtos().size());
//    }
}
