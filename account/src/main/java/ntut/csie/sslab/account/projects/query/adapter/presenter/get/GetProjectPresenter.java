package ntut.csie.sslab.account.projects.query.adapter.presenter.get;

import ntut.csie.sslab.account.projects.command.entity.Project;
import ntut.csie.sslab.account.projects.query.adapter.presenter.DomainToDtoMapper;
import ntut.csie.sslab.account.projects.query.adapter.presenter.ProjectDto;
import ntut.csie.sslab.account.projects.query.usecase.get.GetProjectOutput;
import ntut.csie.sslab.ddd.adapter.presenter.Presenter;
import ntut.csie.sslab.ddd.usecase.Result;

public class GetProjectPresenter extends Result implements Presenter<GetProjectViewModel>,GetProjectOutput {
    private Project project;
    private GetProjectViewModel viewModel;


    @Override
    public GetProjectViewModel buildViewModel() {
        ProjectDto projectDto = new ProjectDto(
                project.getId(),
                project.getName(),
                DomainToDtoMapper.sortRepoDtoByOwnerNameAndRepoName(project.getRepositories()),
                DomainToDtoMapper.sortMemberDtoByRoleAndusername(project.getProjectMembers()));
        viewModel = new GetProjectViewModel(projectDto);
        return viewModel;
    }


    @Override
    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public Project getProject() {
        return project;
    }


}
