package ntut.csie.sslab.account.projects.command.adapter.repository;


import javax.persistence.*;
import java.util.Objects;

@Entity
public class RepositoryData {
    @EmbeddedId
    private RepositoryId repositoryId;

//    @Column(name="repository_order", nullable = false)
//    private int order;

    public RepositoryData() {
    }

    public RepositoryData(String ownerName, String repositoryName) {
        this.repositoryId = new RepositoryId(ownerName, repositoryName);
//        this.order = order;
    }

    public String getOwnerName(){ return repositoryId.getOwnerName();}

    public String getRepositoryName(){return repositoryId.getRepositoryName();}

//    public int getOrder() {
//        return order;
//    }

    public void setOwnerName(String ownerName) {
        this.repositoryId.setOwnerName(ownerName);
    }

    public void setRepositoryName(String repositoryName){
        this.repositoryId.setRepositoryName(repositoryName);
    }

//    public void setOrder(int order) {
//        this.order = order;
//    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.repositoryId.getOwnerName());
        hash = 79 * hash + Objects.hashCode(this.repositoryId.getRepositoryName());
//        hash = 79 * hash + Objects.hashCode(this.order);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RepositoryData other = (RepositoryData) obj;
        if (!Objects.equals(this.repositoryId, other.repositoryId)) {
            return false;
        }
//        if (!Objects.equals(this.order, other.order)) {
//            return false;
//        }
        return true;
    }
}
