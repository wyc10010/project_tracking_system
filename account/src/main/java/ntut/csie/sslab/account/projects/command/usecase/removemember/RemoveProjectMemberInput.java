package ntut.csie.sslab.account.projects.command.usecase.removemember;

import ntut.csie.sslab.ddd.usecase.Input;

public interface RemoveProjectMemberInput extends Input {
    void setProjectId(String projectId);
    String getProjectId();

    void setUsername(String username);
    String getUsername();
}
