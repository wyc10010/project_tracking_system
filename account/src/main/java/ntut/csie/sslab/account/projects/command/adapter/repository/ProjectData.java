package ntut.csie.sslab.account.projects.command.adapter.repository;


import org.hibernate.annotations.CollectionType;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "project", schema = "account")
public class ProjectData {
    @Id
    @Column(name="project_id")
    private String id;
    @Column(name="project_name")
    private String projectName;

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn(name="project_id_fk")
    @OrderBy("repositoryId.ownerName")
    private Set<RepositoryData> repositoryDataSet;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "project_member_mapping",
            joinColumns = {@JoinColumn(name = "project_id", referencedColumnName = "project_id")})
    @MapKeyColumn(name = "username")
    @Column(name = "role")
    private Map<String, String> projectMembers;

    public ProjectData() {
        repositoryDataSet = new HashSet<>();
        projectMembers = new HashMap<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public List<RepositoryData> getRepositoryDataSet() {
        return new ArrayList<>(repositoryDataSet);
    }

    public void setRepositoryDataSet(List<RepositoryData> repositoryDataList) {
        this.repositoryDataSet = new HashSet<>(repositoryDataList);
    }

    public Map<String, String> getProjectMembers() {
        return projectMembers;
    }

    public void setProjectMembers(Map<String, String> projectMembers) {
        this.projectMembers = projectMembers;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
        hash = 79 * hash + Objects.hashCode(this.projectName);
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
        final ProjectData other = (ProjectData) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.projectName, other.projectName)) {
            return false;
        }
        return true;
    }
}
