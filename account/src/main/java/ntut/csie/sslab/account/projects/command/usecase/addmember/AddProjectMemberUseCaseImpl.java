package ntut.csie.sslab.account.projects.command.usecase.addmember;

import ntut.csie.sslab.account.projects.command.entity.Project;
import ntut.csie.sslab.account.projects.command.entity.Role;
import ntut.csie.sslab.account.projects.command.usecase.ProjectRepository;
import ntut.csie.sslab.account.users.command.entity.User;
import ntut.csie.sslab.account.users.command.usecase.UserRepository;
import ntut.csie.sslab.ddd.model.DomainEvent;
import ntut.csie.sslab.ddd.model.DomainEventBus;
import ntut.csie.sslab.ddd.usecase.cqrs.CqrsCommandOutput;
import ntut.csie.sslab.ddd.usecase.cqrs.ExitCode;

import java.util.Optional;

public class AddProjectMemberUseCaseImpl implements AddProjectMemberUseCase{
    private ProjectRepository projectRepository;
    private UserRepository userRepository;
    private DomainEventBus eventBus;

    public AddProjectMemberUseCaseImpl(ProjectRepository projectRepository, UserRepository userRepository, DomainEventBus eventBus) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
        this.eventBus = eventBus;
    }

    @Override
    public void execute(AddProjectMemberInput input, CqrsCommandOutput output) {
        Optional<Project> project = projectRepository.findById(input.getProjectId());
        Optional<User> user = userRepository.findById(input.getUsername());
        if(!project.isPresent()){
            output.setExitCode(ExitCode.FAILURE)
                    .setMessage("The project does not exist in the system.");
        }else if (project.get().getProjectMembers().containsKey(input.getUsername())){
            output.setExitCode(ExitCode.FAILURE)
                    .setMessage("The user is already in the list if the project.");
        }else if(!user.isPresent()){
            output.setExitCode(ExitCode.FAILURE)
                    .setMessage("The user does not exist in the system.");
        } else {
            project.get().addProjectMember(input.getUsername(), Role.valueOf(input.getRole()));
            projectRepository.save(project.get());
            eventBus.postAll(project.get());
            output.setId(project.get().getId())
                    .setExitCode(ExitCode.SUCCESS);
        }
    }


    @Override
    public AddProjectMemberInput newInput() {
        return new AddProjectMemberInputImpl();
    }

    private static class AddProjectMemberInputImpl implements AddProjectMemberInput{
        private String projectId;
        private String username;
        private String role;

        @Override
        public String getProjectId() {
            return projectId;
        }

        @Override
        public void setProjectId(String projectId) {
            this.projectId = projectId;
        }

        @Override
        public String getUsername() {
            return username;
        }

        @Override
        public void setUsername(String username) {
            this.username = username;
        }

        @Override
        public String getRole() {
            return role;
        }

        @Override
        public void setRole(String role) {
            this.role = role;
        }
    }


}
