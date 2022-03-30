package ntut.csie.sslab.account.projects.query.usecase.getcontent;

import ntut.csie.sslab.account.projects.command.entity.Project;
import ntut.csie.sslab.ddd.usecase.Output;

import java.util.List;

public interface GetDashboardContentOutput extends Output {
    void setProjects(List<Project> projects);
    List<Project> getProjects();
}
