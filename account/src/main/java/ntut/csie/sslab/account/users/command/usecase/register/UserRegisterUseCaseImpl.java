package ntut.csie.sslab.account.users.command.usecase.register;

import ntut.csie.sslab.account.users.command.entity.User;
import ntut.csie.sslab.account.users.command.usecase.UserRepository;
import ntut.csie.sslab.ddd.model.DomainEventBus;
import ntut.csie.sslab.ddd.usecase.cqrs.CqrsCommandOutput;
import ntut.csie.sslab.ddd.usecase.cqrs.ExitCode;

import java.util.UUID;

public class UserRegisterUseCaseImpl implements UserRegisterUseCase{
    private UserRepository userRepository;
    private DomainEventBus eventBus;

    public UserRegisterUseCaseImpl(UserRepository userRepository, DomainEventBus eventBus) {
        this.userRepository = userRepository;
        this.eventBus = eventBus;
    }


    @Override
    public void execute(UserRegisterInput input, CqrsCommandOutput output) {
        User user = userRepository.getUserByUsername(input.getUsername()).orElse(null);
        if(null == user) {
            user = new User(input.getUsername());
            userRepository.save(user);
//            eventBus.postAll(user);
            output.setMessage("User Registered!");
        }else{
            output.setMessage("The username has already existed.");
        }
        output.setExitCode(ExitCode.SUCCESS);
    }

    @Override
    public UserRegisterInput newInput() {
        return new UserRegisterInputImpl();
    }

    private class UserRegisterInputImpl implements UserRegisterInput{
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
