package ntut.csie.sslab.account.projects.command.usecase.removeproject;

import ntut.csie.sslab.account.projects.command.entity.Project;
import ntut.csie.sslab.account.projects.command.usecase.ProjectRepository;
import ntut.csie.sslab.ddd.usecase.cqrs.CqrsCommandOutput;
import ntut.csie.sslab.ddd.usecase.cqrs.ExitCode;

import java.util.Optional;

public class RemoveProjectUseCaseImpl implements RemoveProjectUseCase{
    private ProjectRepository projectRepository;

    public RemoveProjectUseCaseImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }


    @Override
    public void execute(RemoveProjectInput input, CqrsCommandOutput output) {
        Optional<Project> project = projectRepository.findById(input.getProjectId());
        if(!project.isPresent()){
            output.setExitCode(ExitCode.FAILURE).setMessage("The project does not exist.");
        }else{
            projectRepository.deleteById(input.getProjectId());
            output.setId(project.get().getId()).setExitCode(ExitCode.SUCCESS);
        }
    }

    @Override
    public RemoveProjectInput newInput() {
        return new RemoveProjectInputImpl();
    }

    private class RemoveProjectInputImpl implements RemoveProjectInput{
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
