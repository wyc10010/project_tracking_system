package ntut.csie.sslab.account.projects.command.usecase.addrepository;

import ntut.csie.sslab.account.projects.command.entity.Project;
import ntut.csie.sslab.account.projects.command.usecase.ProjectRepository;
import ntut.csie.sslab.account.projects.command.usecase.add.AddProjectInput;
import ntut.csie.sslab.ddd.model.DomainEventBus;
import ntut.csie.sslab.ddd.usecase.cqrs.CqrsCommandOutput;
import ntut.csie.sslab.ddd.usecase.cqrs.ExitCode;

import java.util.Optional;

public class AddRepositoryUseCaseImpl implements AddRepositoryUseCase{
    private ProjectRepository projectRepository;
    private DomainEventBus eventBus;

    public AddRepositoryUseCaseImpl(ProjectRepository projectRepository, DomainEventBus eventBus) {
        this.projectRepository = projectRepository;
        this.eventBus = eventBus;
    }

    @Override
    public void execute(AddRepositoryInput input, CqrsCommandOutput output) {
        Optional<Project> project = projectRepository.findById(input.getProjectId());
        if(!project.isPresent()){
            output.setExitCode(ExitCode.FAILURE)
                .setMessage("The project does not exist in the system.");
        }else {
            project.get().addRepository(input.getOwnerName(), input.getRepositoryName());
            projectRepository.save(project.get());
            eventBus.postAll(project.get());
            output.setExitCode(ExitCode.SUCCESS);
            output.setId(project.get().getId());
        }
    }
    @Override
    public AddRepositoryInput newInput() {
        return new AddRepositoryInputImpl();
    }

    private static class AddRepositoryInputImpl implements AddRepositoryInput{
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
