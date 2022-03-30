package ntut.csie.sslab.account.projects.command.adapter.repository;

import ntut.csie.sslab.account.projects.command.entity.Project;
import ntut.csie.sslab.account.projects.command.usecase.ProjectRepository;
import java.util.List;
import java.util.Optional;

public class ProjectRepositoryImpl implements ProjectRepository {

    private ProjectRepositoryPeer peer;

    public ProjectRepositoryImpl(ProjectRepositoryPeer peer) {
        this.peer = peer;
    }

    @Override
    public List<Project> findAll() {
        return null;
    }

    @Override
    public Optional<Project> findById(String id) {
        return peer.findById(id).map(ProjectMapper::transformToDomain_);
    }

    @Override
    public void save(Project project) {
        ProjectData data = ProjectMapper.transformToData(project);
        peer.save(data);
    }

    @Override
    public void deleteById(String s) {
        peer.deleteById(s);

    }


    @Override
    public List<Project> findAllByUsername(String username) {
        return ProjectMapper.transformToDomain(peer.findAllByUsername(username));
    }
}
