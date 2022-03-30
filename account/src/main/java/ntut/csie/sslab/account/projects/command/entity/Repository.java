package ntut.csie.sslab.account.projects.command.entity;

public class Repository {
    private String ownerName;
    private String repositoryName;

    public Repository(String ownerName, String repositoryName) {
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
