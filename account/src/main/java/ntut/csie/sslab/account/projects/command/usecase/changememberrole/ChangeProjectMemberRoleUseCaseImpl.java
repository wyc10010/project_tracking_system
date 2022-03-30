package ntut.csie.sslab.account.projects.command.usecase.changememberrole;

import ntut.csie.sslab.account.projects.command.entity.Project;
import ntut.csie.sslab.account.projects.command.entity.Role;
import ntut.csie.sslab.account.projects.command.usecase.ProjectRepository;
import ntut.csie.sslab.ddd.usecase.cqrs.CqrsCommandOutput;
import ntut.csie.sslab.ddd.usecase.cqrs.ExitCode;

import java.util.Collections;
import java.util.Optional;

public class ChangeProjectMemberRoleUseCaseImpl implements ChangeProjectMemberRoleUseCase{
    private ProjectRepository projectRepository;

    public ChangeProjectMemberRoleUseCaseImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public void execute(ChangeProjectMemberRoleInput input, CqrsCommandOutput output) {
        Optional<Project> project = projectRepository.findById(input.getProjectId());
        Role role = project.get().getProjectMembers().get(input.getUsername());
        int count = Collections.frequency(project.get().getProjectMembers().values(), Role.PROJECT_MANAGER);
        if(!project.isPresent()){
            output.setExitCode(ExitCode.FAILURE)
                    .setMessage("The project does not exist.");
        }else if(!project.get().getProjectMembers().containsKey(input.getUsername())) {
            output.setExitCode(ExitCode.FAILURE)
                    .setMessage("The user is not in the project member list.");
        }else if(role == Role.PROJECT_MANAGER && count == 1){
            output.setExitCode(ExitCode.FAILURE)
                    .setMessage("There should be at least one manager in the project member list, and your operation would cause no manager in the project member list. Please add a new manager before the operation.");
        }else{
            project.get().changeMemberRole(input.getUsername(), input.getRole());
            projectRepository.save(project.get());
            output.setId(project.get().getId()).setExitCode(ExitCode.SUCCESS);
        }
    }

    @Override
    public ChangeProjectMemberRoleInput newInput() {
        return new ChangeProjectMemberRoleInputImpl();
    }

    private static class ChangeProjectMemberRoleInputImpl implements ChangeProjectMemberRoleInput{
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
