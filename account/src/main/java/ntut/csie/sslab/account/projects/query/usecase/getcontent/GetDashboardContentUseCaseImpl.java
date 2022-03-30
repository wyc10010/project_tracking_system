package ntut.csie.sslab.account.projects.query.usecase.getcontent;

import ntut.csie.sslab.account.projects.command.entity.Project;
import ntut.csie.sslab.account.projects.command.usecase.ProjectRepository;
import ntut.csie.sslab.ddd.usecase.cqrs.ExitCode;

import java.util.List;

public class GetDashboardContentUseCaseImpl implements GetDashboardContentUseCase{
    private ProjectRepository projectRepository;

    public GetDashboardContentUseCaseImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }
    //todo user not exist in the system
    @Override
    public void execute(GetDashboardContentInput input, GetDashboardContentOutput output) {
        List<Project> projects = projectRepository.findAllByUsername(input.getUsername());
        output.setExitCode(ExitCode.SUCCESS);
        output.setProjects(projects);
    }

    @Override
    public GetDashboardContentInput newInput() {
        return new GetDashboardContentInputImpl();
    }

    private static class GetDashboardContentInputImpl implements GetDashboardContentInput {
        private String username;
        @Override
        public void setUsername(String username) {
            this.username = username;
        }

        @Override
        public String getUsername() {
            return username;
        }
    }
}
