package ntut.csie.sslab.account.projects.command.usecase.add;

import ntut.csie.sslab.ddd.usecase.Input;

public interface AddProjectInput extends Input {
    void setUsername(String userName);
    String getUserName();
    void setProjectName(String projectName);
    String getProjectName();

}
