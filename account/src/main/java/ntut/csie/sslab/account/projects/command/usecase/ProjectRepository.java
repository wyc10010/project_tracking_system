package ntut.csie.sslab.account.projects.command.usecase;


import ntut.csie.sslab.account.projects.command.entity.Project;
import ntut.csie.sslab.ddd.usecase.AbstractRepository;

import java.util.List;

public interface ProjectRepository extends AbstractRepository<Project, String> {
    List<Project> findAllByUsername(String username);
}
