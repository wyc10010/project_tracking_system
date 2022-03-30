package ntut.csie.sslab.account.projects.command.usecase.removeproject;

import ntut.csie.sslab.ddd.usecase.Input;

public interface RemoveProjectInput extends Input {
    void setProjectId(String projectId);
    String getProjectId();
}
