package ntut.csie.sslab.account.projects.query.usecase.get;

import ntut.csie.sslab.account.projects.command.entity.Project;
import ntut.csie.sslab.ddd.usecase.Output;

public interface GetProjectOutput  extends Output {
    void setProject(Project project);
    Project getProject();
}
