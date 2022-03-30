package ntut.csie.sslab.account.projects.query.adapter.presenter.getcontent;

import ntut.csie.sslab.account.projects.command.entity.Project;
import ntut.csie.sslab.account.projects.query.adapter.presenter.DomainToDtoMapper;
import ntut.csie.sslab.account.projects.query.adapter.presenter.ProjectDto;
import ntut.csie.sslab.account.projects.query.usecase.getcontent.GetDashboardContentOutput;
import ntut.csie.sslab.ddd.adapter.presenter.Presenter;
import ntut.csie.sslab.ddd.usecase.Result;

import java.util.*;

public class GetDashboardContentPresenter extends Result implements Presenter<GetDashboardContentViewModel>, GetDashboardContentOutput {
    private List<Project> projects;
    private GetDashboardContentViewModel viewModel;

    @Override
    public GetDashboardContentViewModel buildViewModel() {
        List<ProjectDto> projectDtos = new ArrayList<>();
        for(Project project: projects){
            projectDtos.add(
                    new ProjectDto(project.getId(),
                            project.getName(),
                            DomainToDtoMapper.sortRepoDtoByOwnerNameAndRepoName(project.getRepositories()),
                            DomainToDtoMapper.sortMemberDtoByRoleAndusername(project.getProjectMembers()))
            );
        }
        viewModel = new GetDashboardContentViewModel(projectDtos);
        return viewModel;
    }
    @Override
    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    @Override
    public List<Project> getProjects() {
        return projects;
    }


}
