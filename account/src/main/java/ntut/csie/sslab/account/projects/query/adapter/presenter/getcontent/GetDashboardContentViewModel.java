package ntut.csie.sslab.account.projects.query.adapter.presenter.getcontent;

import ntut.csie.sslab.account.projects.query.adapter.presenter.ProjectDto;
import ntut.csie.sslab.ddd.adapter.presenter.ViewModel;

import java.util.List;

public class GetDashboardContentViewModel implements ViewModel {
    private List<ProjectDto> projects;
    public GetDashboardContentViewModel(List<ProjectDto> projects) {
        this.projects = projects;
    }
    public List<ProjectDto> getProjects() {
        return projects;
    }
}
