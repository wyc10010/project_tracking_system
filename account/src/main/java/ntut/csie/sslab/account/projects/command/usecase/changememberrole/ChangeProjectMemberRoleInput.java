package ntut.csie.sslab.account.projects.command.usecase.changememberrole;

import ntut.csie.sslab.ddd.usecase.Input;

public interface ChangeProjectMemberRoleInput extends Input {
    void setProjectId(String projectId);
    String getProjectId();

    void setUsername(String username);
    String getUsername();

    void setRole(String role);
    String getRole();
}
