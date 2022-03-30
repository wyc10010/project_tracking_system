package ntut.csie.sslab.account.application.springboot.web.config;

import ntut.csie.sslab.account.projects.command.adapter.repository.ProjectRepositoryImpl;
import ntut.csie.sslab.account.projects.command.adapter.repository.ProjectRepositoryPeer;
import ntut.csie.sslab.account.projects.command.usecase.ProjectRepository;
import ntut.csie.sslab.account.users.command.adapter.repository.UserRepositoryImpl;
import ntut.csie.sslab.account.users.command.adapter.repository.UserRepositoryPeer;
import ntut.csie.sslab.account.users.command.usecase.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@PropertySource(value = "classpath:/application.properties")
@Configuration("AccountRepositoryInjection")
public class RepositoryInjection {
    @Autowired
    private UserRepositoryPeer userRepositoryPeer;
    @Autowired
    private ProjectRepositoryPeer projectRepositoryPeer;

//    @Autowired
//    public void setUserRepositoryPeer(UserRepositoryPeer userRepositoryPeer) {
//        this.userRepositoryPeer = userRepositoryPeer;
//    }
//
    @Bean
    public UserRepository userRepository(){ return new UserRepositoryImpl(userRepositoryPeer); }

    @Bean
    public ProjectRepository projectRepository(){ return new ProjectRepositoryImpl(projectRepositoryPeer); }

//    @Bean
//    public UserQueryRepository userQueryRepository(){ return new UserQueryRepositoryImpl(jdbcTemplate); }
//
//    @Bean
//    public Encrypt encrypt(){ return new BCryptImpl();}


}
