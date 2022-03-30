package ntut.csie.sslab.account.projects.query.usecase.get;

import ntut.csie.sslab.ddd.usecase.Input;

public interface GetProjectInput extends Input {
    void setProjectId(String projectId);
    String getProjectId();
}
