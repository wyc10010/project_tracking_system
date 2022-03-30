package ntut.csie.sslab.account.application.springboot.web.config;

import ntut.csie.sslab.account.projects.command.usecase.ProjectRepository;
import ntut.csie.sslab.account.projects.command.usecase.add.AddProjectUseCase;
import ntut.csie.sslab.account.projects.command.usecase.add.AddProjectUseCaseImpl;
import ntut.csie.sslab.account.projects.command.usecase.addmember.AddProjectMemberUseCase;
import ntut.csie.sslab.account.projects.command.usecase.addmember.AddProjectMemberUseCaseImpl;
import ntut.csie.sslab.account.projects.command.usecase.addrepository.AddRepositoryUseCase;
import ntut.csie.sslab.account.projects.command.usecase.addrepository.AddRepositoryUseCaseImpl;
import ntut.csie.sslab.account.projects.command.usecase.changememberrole.ChangeProjectMemberRoleUseCase;
import ntut.csie.sslab.account.projects.command.usecase.changememberrole.ChangeProjectMemberRoleUseCaseImpl;
import ntut.csie.sslab.account.projects.command.usecase.removemember.RemoveProjectMemberUseCase;
import ntut.csie.sslab.account.projects.command.usecase.removemember.RemoveProjectMemberUseCaseImpl;
import ntut.csie.sslab.account.projects.command.usecase.removeproject.RemoveProjectUseCase;
import ntut.csie.sslab.account.projects.command.usecase.removeproject.RemoveProjectUseCaseImpl;
import ntut.csie.sslab.account.projects.command.usecase.removerepository.RemoveRepositoryUseCase;
import ntut.csie.sslab.account.projects.command.usecase.removerepository.RemoveRepositoryUseCaseImpl;
import ntut.csie.sslab.account.projects.query.usecase.get.GetProjectUseCase;
import ntut.csie.sslab.account.projects.query.usecase.get.GetProjectUseCaseImpl;
import ntut.csie.sslab.account.projects.query.usecase.getcontent.GetDashboardContentUseCase;
import ntut.csie.sslab.account.projects.query.usecase.getcontent.GetDashboardContentUseCaseImpl;
import ntut.csie.sslab.account.users.command.usecase.UserRepository;

import ntut.csie.sslab.account.users.command.usecase.register.UserRegisterUseCase;
import ntut.csie.sslab.account.users.command.usecase.register.UserRegisterUseCaseImpl;

import ntut.csie.sslab.ddd.model.DomainEventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("AccountUseCaseInjection")
public class UseCaseInjection {
    @Autowired
    private DomainEventBus eventBus;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProjectRepository projectRepository;

//    @Autowired
//    private TokenGenerator tokenGenerator;
//
//    @Autowired
//    public void setEventBus(DomainEventBus eventBus) { this.eventBus = eventBus; }
//
//    @Autowired
//    public void setUserRepository(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @Autowired
//    public void setUserQueryRepository(UserQueryRepository userQueryRepository) {
//        this.userQueryRepository = userQueryRepository;
//    }
//
//    @Autowired
//    public void setEncrypt(Encrypt encrypt) {
//        this.encrypt = encrypt;
//    }
//
    @Bean
    public UserRegisterUseCase userRegisterUseCase(){ return new UserRegisterUseCaseImpl(userRepository, eventBus);}

    @Bean
    public AddProjectUseCase addProjectUseCase(){ return new AddProjectUseCaseImpl(projectRepository, eventBus);}

    @Bean
    public AddRepositoryUseCase addRepositoryUseCase(){ return new AddRepositoryUseCaseImpl(projectRepository, eventBus);}

    @Bean
    public AddProjectMemberUseCase addProjectMemberUseCase(){return new AddProjectMemberUseCaseImpl(projectRepository, userRepository, eventBus);}

    @Bean
    public GetDashboardContentUseCase getDashboardContentUseCase(){return new GetDashboardContentUseCaseImpl(projectRepository);}

    @Bean
    public GetProjectUseCase getProjectUseCase(){return new GetProjectUseCaseImpl(projectRepository);}

    @Bean
    public ChangeProjectMemberRoleUseCase changeProjectMemberRoleUseCase() {return new ChangeProjectMemberRoleUseCaseImpl(projectRepository);}

    @Bean
    public RemoveProjectMemberUseCase removeProjectMemberUseCase() {return new RemoveProjectMemberUseCaseImpl(projectRepository);}

    @Bean
    public RemoveRepositoryUseCase removeRepositoryUseCase() {return new RemoveRepositoryUseCaseImpl(projectRepository);}

    @Bean
    public RemoveProjectUseCase removeProjectUseCase() {return new RemoveProjectUseCaseImpl(projectRepository);}

}
