package ntut.csie.sslab.account.projects.command.usecase.add;

import ntut.csie.sslab.account.projects.command.entity.Project;
import ntut.csie.sslab.account.projects.command.entity.Role;
import ntut.csie.sslab.account.projects.command.usecase.ProjectRepository;
import ntut.csie.sslab.ddd.model.DomainEventBus;
import ntut.csie.sslab.ddd.usecase.cqrs.CqrsCommandOutput;
import ntut.csie.sslab.ddd.usecase.cqrs.ExitCode;

import java.util.UUID;

public class AddProjectUseCaseImpl implements AddProjectUseCase{
    private ProjectRepository projectRepository;
    private DomainEventBus eventBus;

    public AddProjectUseCaseImpl(ProjectRepository projectRepository, DomainEventBus eventBus) {
        this.projectRepository = projectRepository;
        this.eventBus = eventBus;
    }

    @Override
    public void execute(AddProjectInput input, CqrsCommandOutput output) {
        Project project = new Project(UUID.randomUUID().toString(), input.getProjectName(), input.getUserName());
        projectRepository.save(project);
        eventBus.postAll(project);
        output.setId(project.getId());
        output.setExitCode(ExitCode.SUCCESS);
    }

    @Override
    public AddProjectInput newInput() {
        return new AddProjectInputImpl();
    }

    private static class AddProjectInputImpl implements AddProjectInput{
        private String userName;
        private String projectName;

        @Override
        public String getUserName() {
            return userName;
        }

        @Override
        public void setUsername(String userName) {
            this.userName = userName;
        }

        @Override
        public String getProjectName() {
            return projectName;
        }

        @Override
        public void setProjectName(String projectName) {
            this.projectName = projectName;
        }
    }
}
