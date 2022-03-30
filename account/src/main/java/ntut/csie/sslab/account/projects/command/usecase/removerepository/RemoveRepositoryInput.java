package ntut.csie.sslab.account.projects.command.usecase.removerepository;

import ntut.csie.sslab.ddd.usecase.Input;

public interface RemoveRepositoryInput extends Input {
    void setProjectId(String projectId);
    String getProjectId();

    void setOwnerName(String ownerName);
    String getOwnerName();

    void setRepositoryName(String repositoryName);
    String getRepositoryName();
}
