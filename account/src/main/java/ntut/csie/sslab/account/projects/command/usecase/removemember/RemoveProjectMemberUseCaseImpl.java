package ntut.csie.sslab.account.projects.command.usecase.removemember;

import ntut.csie.sslab.account.projects.command.entity.Project;
import ntut.csie.sslab.account.projects.command.entity.Role;
import ntut.csie.sslab.account.projects.command.usecase.ProjectRepository;
import ntut.csie.sslab.ddd.usecase.cqrs.CqrsCommandOutput;
import ntut.csie.sslab.ddd.usecase.cqrs.ExitCode;

import java.util.Collections;
import java.util.Optional;

public class RemoveProjectMemberUseCaseImpl implements RemoveProjectMemberUseCase{
    private ProjectRepository projectRepository;

    public RemoveProjectMemberUseCaseImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public void execute(RemoveProjectMemberInput input, CqrsCommandOutput output) {
        Optional<Project> project = projectRepository.findById(input.getProjectId());
        Role role = project.get().getProjectMembers().get(input.getUsername());
        int count = Collections.frequency(project.get().getProjectMembers().values(), Role.PROJECT_MANAGER);
        if(!project.isPresent()){
            output.setExitCode(ExitCode.FAILURE).setMessage("The project does not exist.");
        }else if(!project.get().getProjectMembers().containsKey(input.getUsername())){
            output.setExitCode(ExitCode.FAILURE).setMessage("The member is not in the member list.");
        }else if(role == Role.PROJECT_MANAGER && count == 1){
            output.setExitCode(ExitCode.FAILURE).setMessage("There would be no manager left in the member list. Please add a manager before the operation.");
        }
        else{
            project.get().removeProjectMember(input.getUsername());
            projectRepository.save(project.get());
            output.setId(project.get().getId()).setExitCode(ExitCode.SUCCESS);
        }
    }

    @Override
    public RemoveProjectMemberInput newInput() {
        return new RemoveProjectMemberInputImpl();
    }

    private class RemoveProjectMemberInputImpl implements RemoveProjectMemberInput{
        private String projectId;
        private String username;

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
    }
}
