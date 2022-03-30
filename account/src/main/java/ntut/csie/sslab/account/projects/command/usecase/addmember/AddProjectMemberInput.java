package ntut.csie.sslab.account.projects.command.usecase.addmember;

import ntut.csie.sslab.ddd.usecase.Input;

public interface AddProjectMemberInput extends Input {
    void setProjectId(String projectId);
    String getProjectId();

    void setUsername(String username);
    String getUsername();

    void setRole(String role);
    String getRole();
}
