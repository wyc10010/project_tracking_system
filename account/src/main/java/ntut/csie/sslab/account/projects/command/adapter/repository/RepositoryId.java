package ntut.csie.sslab.account.projects.command.adapter.repository;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RepositoryId implements Serializable {
    @Column(name = "owner_name", nullable = false)
    private String ownerName;

    @Column(name = "repository_name", nullable = false)
    private String repositoryName;

    public RepositoryId() {
    }

    public RepositoryId(String ownerName, String repositoryName) {
        this.ownerName = ownerName;
        this.repositoryName = repositoryName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public void setRepositoryName(String repositoryName) {
        this.repositoryName = repositoryName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getRepositoryName() {
        return repositoryName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RepositoryId)) return false;
        RepositoryId that = (RepositoryId) o;
        return Objects.equals(getOwnerName(), that.getOwnerName()) &&
                Objects.equals(getRepositoryName(), that.getRepositoryName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOwnerName(), getRepositoryName());
    }
}
