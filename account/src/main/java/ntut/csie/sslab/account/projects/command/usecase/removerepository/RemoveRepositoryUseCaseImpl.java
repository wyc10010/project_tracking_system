package ntut.csie.sslab.account.projects.command.usecase.removerepository;

import ntut.csie.sslab.account.projects.command.entity.Project;
import ntut.csie.sslab.account.projects.command.entity.Repository;
import ntut.csie.sslab.account.projects.command.usecase.ProjectRepository;
import ntut.csie.sslab.ddd.usecase.cqrs.CqrsCommandOutput;
import ntut.csie.sslab.ddd.usecase.cqrs.ExitCode;

import java.util.List;
import java.util.Optional;

public class RemoveRepositoryUseCaseImpl implements RemoveRepositoryUseCase{
    private ProjectRepository projectRepository;

    public RemoveRepositoryUseCaseImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }


    @Override
    public void execute(RemoveRepositoryInput input, CqrsCommandOutput output) {
        Optional<Project> project =  projectRepository.findById(input.getProjectId());
        if(!project.isPresent()){
            output.setExitCode(ExitCode.FAILURE).setMessage("The project does not exist.");
        }else if(!isSameRepository(project.get().getRepositories(), input.getOwnerName(), input.getRepositoryName())){
            output.setExitCode(ExitCode.FAILURE).setMessage("The repository does not exist in the project.");
        }else{
            project.get().removeRepository(input.getOwnerName(), input.getRepositoryName());
            projectRepository.save(project.get());
            output.setId(project.get().getId()).setExitCode(ExitCode.SUCCESS);
        }
    }

    private boolean isSameRepository(List<Repository> repositories, String ownerName, String repositoryName){
        return repositories.stream().filter(repo -> repo.getOwnerName().equals(ownerName) && repo.getRepositoryName().equals(repositoryName)).findFirst().isPresent();
    }

    @Override
    public RemoveRepositoryInput newInput() {
        return new RemoveRepositoryImpl();
    }

    private class RemoveRepositoryImpl implements RemoveRepositoryInput{
        private String projectId;
        private String ownerName;
        private String repositoryName;

        @Override
        public String getProjectId() {
            return projectId;
        }

        @Override
        public void setProjectId(String projectId) {
            this.projectId = projectId;
        }

        @Override
        public String getOwnerName() {
            return ownerName;
        }

        @Override
        public void setOwnerName(String ownerName) {
            this.ownerName = ownerName;
        }

        @Override
        public String getRepositoryName() {
            return repositoryName;
        }

        @Override
        public void setRepositoryName(String repositoryName) {
            this.repositoryName = repositoryName;
        }
    }
}
