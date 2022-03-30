package ntut.csie.sslab.account.projects.command.usecase.addrepository;

import ntut.csie.sslab.ddd.usecase.Input;

public interface AddRepositoryInput extends Input {
    String getProjectId();
    void setProjectId(String projectId);

    String getOwnerName();
    void setOwnerName(String ownerName);

    String getRepositoryName();
    void setRepositoryName(String repositoryName);
}
