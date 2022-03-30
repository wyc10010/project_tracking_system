package ntut.csie.sslab.account.projects.query.usecase.get;

import ntut.csie.sslab.account.projects.command.entity.Project;
import ntut.csie.sslab.account.projects.command.usecase.ProjectRepository;
import ntut.csie.sslab.ddd.usecase.cqrs.ExitCode;

public class GetProjectUseCaseImpl implements GetProjectUseCase{
    private ProjectRepository projectRepository;

    public GetProjectUseCaseImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public void execute(GetProjectInput input, GetProjectOutput output) {
        Project project = projectRepository.findById(input.getProjectId()).get();
        output.setExitCode(ExitCode.SUCCESS);
        output.setProject(project);
    }

    @Override
    public GetProjectInput newInput() {
        return new GetProjectInputImpl();
    }
    private static class GetProjectInputImpl implements GetProjectInput{
        private String projectId;

        @Override
        public String getProjectId() {
            return projectId;
        }

        @Override
        public void setProjectId(String projectId) {
            this.projectId = projectId;
        }
    }
}
