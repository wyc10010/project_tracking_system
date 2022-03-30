package ntut.csie.sslab.account.projects.command.adapter.repository;

import ntut.csie.sslab.account.projects.command.entity.Project;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepositoryPeer extends CrudRepository<ProjectData, String> {
    @Query(value = "SELECT * FROM project_member_mapping JOIN project " +
            "ON project_member_mapping.project_id = project.project_id" +
            " WHERE username = :username",
            nativeQuery = true
    )
    public List<ProjectData> findAllByUsername(@Param("username") String username);
}
