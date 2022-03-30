package ntut.csie.sslab.account.application.springboot.web.config;


import ntut.csie.sslab.account.users.command.usecase.Encrypt;
import ntut.csie.sslab.account.users.command.usecase.NotifyUser;
import ntut.csie.sslab.account.users.command.usecase.UserRepository;
import ntut.csie.sslab.account.users.query.usecase.UserQueryRepository;
import ntut.csie.sslab.account.users.query.usecase.user.get.GetUserUseCase;
import ntut.csie.sslab.account.users.query.usecase.user.get.GetUserUseCaseImpl;
import ntut.csie.sslab.ddd.model.DomainEventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("AccountEventBusInjection")
public class AccountEventBusInjection {

    @Bean(name="accountEventBus")
    public DomainEventBus eventBus() {
        return new DomainEventBus();
    }

    @Bean
    public NotifyUser notifyUser() {return new NotifyUser();}
}
