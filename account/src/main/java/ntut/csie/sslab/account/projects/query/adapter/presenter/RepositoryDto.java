package ntut.csie.sslab.account.projects.query.adapter.presenter;

public class RepositoryDto {
    private String ownerName;
    private String repositoryName;

    public RepositoryDto(String ownerName, String repositoryName) {
        this.ownerName = ownerName;
        this.repositoryName = repositoryName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getRepositoryName() {
        return repositoryName;
    }
}
