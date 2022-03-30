package ntut.csie.sslab.account.projects.query.adapter.presenter.get;

import ntut.csie.sslab.account.projects.query.adapter.presenter.ProjectDto;
import ntut.csie.sslab.ddd.adapter.presenter.ViewModel;

public class GetProjectViewModel implements ViewModel {
    private ProjectDto project;

    public GetProjectViewModel(ProjectDto project) {
        this.project = project;
    }

    public ProjectDto getProject() {
        return project;
    }
}
