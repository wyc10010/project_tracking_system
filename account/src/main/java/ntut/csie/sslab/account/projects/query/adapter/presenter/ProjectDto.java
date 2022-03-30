package ntut.csie.sslab.account.projects.query.adapter.presenter;

import java.util.List;

public class ProjectDto {
    private String projectId;
    private String projectName;
    private List<RepositoryDto> repositories;
    private List<MemberDto> members;

    public ProjectDto(String projectId,
                      String projectName,
                      List<RepositoryDto> repositories,
                      List<MemberDto> members) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.repositories = repositories;
        this.members = members;
    }

    public String getProjectId() {
        return projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public List<RepositoryDto> getRepositories() {
        return repositories;
    }

    public List<MemberDto> getMembers() {
        return members;
    }
}
