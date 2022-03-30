package ntut.csie.sslab.account;

//import ntut.csie.sslab.account.users.command.adapter.repository.UserRepositoryImpl;
import ntut.csie.sslab.account.projects.command.adapter.repository.ProjectRepositoryImpl;
import ntut.csie.sslab.account.projects.command.adapter.repository.ProjectRepositoryPeer;
import ntut.csie.sslab.account.projects.command.entity.Role;
import ntut.csie.sslab.account.projects.command.usecase.ProjectRepository;
import ntut.csie.sslab.account.projects.command.usecase.add.AddProjectInput;
import ntut.csie.sslab.account.projects.command.usecase.add.AddProjectUseCase;
import ntut.csie.sslab.account.projects.command.usecase.add.AddProjectUseCaseImpl;
import ntut.csie.sslab.account.projects.command.usecase.addmember.AddProjectMemberInput;
import ntut.csie.sslab.account.projects.command.usecase.addmember.AddProjectMemberUseCase;
import ntut.csie.sslab.account.projects.command.usecase.addmember.AddProjectMemberUseCaseImpl;
import ntut.csie.sslab.account.projects.command.usecase.addrepository.AddRepositoryInput;
import ntut.csie.sslab.account.projects.command.usecase.addrepository.AddRepositoryUseCase;
import ntut.csie.sslab.account.projects.command.usecase.addrepository.AddRepositoryUseCaseImpl;
import ntut.csie.sslab.account.projects.command.usecase.changememberrole.ChangeProjectMemberRoleInput;
import ntut.csie.sslab.account.projects.command.usecase.changememberrole.ChangeProjectMemberRoleUseCase;
import ntut.csie.sslab.account.projects.command.usecase.changememberrole.ChangeProjectMemberRoleUseCaseImpl;
import ntut.csie.sslab.account.projects.command.usecase.removemember.RemoveProjectMemberInput;
import ntut.csie.sslab.account.projects.command.usecase.removemember.RemoveProjectMemberUseCase;
import ntut.csie.sslab.account.projects.command.usecase.removemember.RemoveProjectMemberUseCaseImpl;
import ntut.csie.sslab.account.projects.command.usecase.removeproject.RemoveProjectInput;
import ntut.csie.sslab.account.projects.command.usecase.removeproject.RemoveProjectUseCase;
import ntut.csie.sslab.account.projects.command.usecase.removeproject.RemoveProjectUseCaseImpl;
import ntut.csie.sslab.account.projects.command.usecase.removerepository.RemoveRepositoryInput;
import ntut.csie.sslab.account.projects.command.usecase.removerepository.RemoveRepositoryUseCase;
import ntut.csie.sslab.account.projects.command.usecase.removerepository.RemoveRepositoryUseCaseImpl;
import ntut.csie.sslab.account.projects.query.adapter.presenter.getcontent.GetDashboardContentPresenter;
import ntut.csie.sslab.account.projects.query.usecase.get.GetProjectUseCase;
import ntut.csie.sslab.account.projects.query.usecase.get.GetProjectUseCaseImpl;
import ntut.csie.sslab.account.projects.query.usecase.getcontent.GetDashboardContentInput;
import ntut.csie.sslab.account.projects.query.usecase.getcontent.GetDashboardContentUseCase;
import ntut.csie.sslab.account.projects.query.usecase.getcontent.GetDashboardContentUseCaseImpl;
import ntut.csie.sslab.account.users.command.adapter.repository.UserRepositoryImpl;
import ntut.csie.sslab.account.users.command.adapter.repository.UserRepositoryPeer;

import ntut.csie.sslab.account.users.command.usecase.NotifyUser;
import ntut.csie.sslab.account.users.command.usecase.UserRepository;

import ntut.csie.sslab.account.users.command.usecase.register.UserRegisterInput;
import ntut.csie.sslab.account.users.command.usecase.register.UserRegisterUseCase;
import ntut.csie.sslab.account.users.command.usecase.register.UserRegisterUseCaseImpl;

        import ntut.csie.sslab.ddd.adapter.presenter.cqrs.CqrsCommandPresenter;
import ntut.csie.sslab.ddd.model.DomainEventBus;
import ntut.csie.sslab.ddd.usecase.cqrs.CqrsCommandOutput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

import static org.junit.Assert.assertEquals;


// 不能使用這個 annotation，用了之後 OR Mapping 的測試結果是不正確的
//@DataJpaTest

@SpringBootTest
//@RunWith(SpringRunner.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes= JpaApplicationTest.class)
@TestPropertySource(locations = "classpath:test.properties")
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
// 加上這個就不會每次跑完test method自動 rollback，才可以看到測試資料被加入資料庫
//@Rollback(false)
public abstract class AbstractSpringBootJpaTest {

    public static final long WAITING = 1000;
    public DomainEventBus domainEventBus;

    public NotifyUser notifyUser;

    public UserRepository userRepository;
    public ProjectRepository projectRepository;

    public String username;
    public String userId;
    public String projectName;
    public String projectId;
    public String ownerName;
    public String repositoryName;

    public String anotherUser = "anotherUser";

    public String project1Id;
    public String project2Id;
    public String project3Id;

    public String project1Name = "project1";
    public String project2Name = "project2";
    public String project3Name = "project3";

    public String ownerName1 = "owerName1";
    public String ownerName2 = "owerName2";
    public String ownerName3 = "owerName3";
    public String ownerName4 = "owerName4";

    public String repositoryName1 = "repositoryName1";
    public String repositoryName2 = "repositoryName2";
    public String repositoryName3 = "repositoryName3";
    public String repositoryName4 = "repositoryName4";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserRepositoryPeer userRepositoryPeer;

    @Autowired
    private ProjectRepositoryPeer projectRepositoryPeer;

    @BeforeEach
    public void setUp() {
        userRepository = new UserRepositoryImpl(userRepositoryPeer);
        projectRepository = new ProjectRepositoryImpl(projectRepositoryPeer);
        domainEventBus = new DomainEventBus();
//        notifyUser = new NotifyUser(newCommitProjectUseCase());

//        domainEventBus.register(notifyUser);
        username = "username";
        userId = UUID.randomUUID().toString();

        projectName = "projectName";
        projectId = UUID.randomUUID().toString();

        ownerName = "owner";
        repositoryName = "repository";
    }


    public UserRegisterUseCase newUserRegisterUseCase() { return new UserRegisterUseCaseImpl(userRepository, domainEventBus);}
    public AddProjectUseCase newAddProjectUseCase() { return new AddProjectUseCaseImpl(projectRepository, domainEventBus);}
    public AddRepositoryUseCase newAddRepositoryUseCase() {return new AddRepositoryUseCaseImpl(projectRepository, domainEventBus);}
    public AddProjectMemberUseCase newAddProjectMemberUseCase() {return new AddProjectMemberUseCaseImpl(projectRepository, userRepository, domainEventBus); }
    public GetDashboardContentUseCase newGetDashboardContentUseCase() { return new GetDashboardContentUseCaseImpl(projectRepository);}
    public GetProjectUseCase newGetProjectUseCase(){return new GetProjectUseCaseImpl(projectRepository);}
    public ChangeProjectMemberRoleUseCase newChangeProjectMemberRoleUseCase() {return new ChangeProjectMemberRoleUseCaseImpl(projectRepository);}
    public RemoveProjectMemberUseCase newRemoveProjectMemberUseCase(){return new RemoveProjectMemberUseCaseImpl(projectRepository);}
    public RemoveRepositoryUseCase newRemoveRepositoryUseCase(){return new RemoveRepositoryUseCaseImpl(projectRepository);}
    public RemoveProjectUseCase newRemoveProjectUseCase(){return new RemoveProjectUseCaseImpl(projectRepository);}
    public CqrsCommandOutput registerUser(String username) {
        UserRegisterUseCase userRegisterUseCase = newUserRegisterUseCase();
        UserRegisterInput input = userRegisterUseCase.newInput();
        input.setUsername(username);
        CqrsCommandOutput output = CqrsCommandPresenter.newInstance();
        userRegisterUseCase.execute(input, output);
        return output;
    }

    public CqrsCommandOutput addProject(String username, String projectName){
        AddProjectUseCase useCase = newAddProjectUseCase();
        AddProjectInput input =  useCase.newInput();
        CqrsCommandOutput output = CqrsCommandPresenter.newInstance();
        input.setUsername(username);
        input.setProjectName(projectName);
        useCase.execute(input, output);
        return output;
    }

    public CqrsCommandOutput addProjectMemeber(String projectId, String username, String role){
        AddProjectMemberUseCase useCase = newAddProjectMemberUseCase();
        AddProjectMemberInput input = useCase.newInput();
        CqrsCommandOutput output = CqrsCommandPresenter.newInstance();
        input.setProjectId(projectId);
        input.setUsername(username);
        input.setRole(role);
        useCase.execute(input, output);
        return output;
    }

    public CqrsCommandOutput addRepository(String projectId, String ownerName, String repositoryName){
        AddRepositoryUseCase useCase = newAddRepositoryUseCase();
        AddRepositoryInput input = useCase.newInput();
        input.setProjectId(projectId);
        input.setOwnerName(ownerName);
        input.setRepositoryName(repositoryName);
        CqrsCommandOutput output = CqrsCommandPresenter.newInstance();
        useCase.execute(input, output);
        return output;
    }

    public GetDashboardContentPresenter getDashBoardContent(String username){
        GetDashboardContentUseCase useCase = newGetDashboardContentUseCase();
        GetDashboardContentInput input = useCase.newInput();
        input.setUsername(username);
        GetDashboardContentPresenter presenter = new GetDashboardContentPresenter();
        useCase.execute(input, presenter);
        return presenter;
    }

    public CqrsCommandOutput changeMemberRole(String projectId, String username, String role){
        ChangeProjectMemberRoleUseCase changeProjectMemberRoleUseCase = newChangeProjectMemberRoleUseCase();
        ChangeProjectMemberRoleInput input = changeProjectMemberRoleUseCase.newInput();
        CqrsCommandOutput output = CqrsCommandPresenter.newInstance();
        input.setProjectId(projectId);
        input.setUsername(username);
        input.setRole(role);
        changeProjectMemberRoleUseCase.execute(input, output);
        return output;
    }

    public CqrsCommandOutput removeProjectMember(String projectId, String username){
        RemoveProjectMemberUseCase useCase = newRemoveProjectMemberUseCase();
        RemoveProjectMemberInput input = useCase.newInput();
        CqrsCommandOutput output = CqrsCommandPresenter.newInstance();
        input.setProjectId(projectId);
        input.setUsername(username);
        useCase.execute(input, output);
        return output;
    }

    public CqrsCommandOutput removeRepository(String projectId, String ownerName, String repositoryName){
        RemoveRepositoryUseCase useCase = newRemoveRepositoryUseCase();
        RemoveRepositoryInput input = useCase.newInput();
        CqrsCommandOutput output = CqrsCommandPresenter.newInstance();
        input.setProjectId(projectId);
        input.setOwnerName(ownerName);
        input.setRepositoryName(repositoryName);
        useCase.execute(input, output);
        return output;
    }

    public CqrsCommandOutput removeProject(String projectId){
        RemoveProjectUseCase useCase = newRemoveProjectUseCase();
        RemoveProjectInput input = useCase.newInput();
        CqrsCommandOutput output = CqrsCommandPresenter.newInstance();
        input.setProjectId(projectId);
        useCase.execute(input, output);
        return output;
    }

    public void makeSomeProjectDataInTheDB(){
        registerUser(username);
        registerUser(anotherUser);
        project1Id = addProject(username, project1Name).getId();
        project2Id = addProject(anotherUser, project2Name).getId();
        project3Id = addProject(anotherUser, project3Name).getId();

        addProjectMemeber(project1Id, anotherUser, Role.PROJECT_USER.toString());
        addProjectMemeber(project3Id, username, Role.PROJECT_USER.toString());

        addRepository(project1Id, ownerName1, repositoryName1);
        addRepository(project1Id, ownerName2, repositoryName2);
        addRepository(project2Id, ownerName3, repositoryName3);
        addRepository(project3Id, ownerName4, repositoryName4);
    }

}
