package ntut.csie.sslab.account.users.command.usecase;

import ntut.csie.sslab.account.AbstractSpringBootJpaTest;
import ntut.csie.sslab.account.users.command.entity.User;
import ntut.csie.sslab.account.users.command.usecase.register.UserRegisterInput;
import ntut.csie.sslab.account.users.command.usecase.register.UserRegisterUseCase;
import ntut.csie.sslab.ddd.adapter.presenter.cqrs.CqrsCommandPresenter;
import ntut.csie.sslab.ddd.usecase.cqrs.CqrsCommandOutput;
import ntut.csie.sslab.ddd.usecase.cqrs.ExitCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@Transactional
public class UserRegisterUseCaseTest extends AbstractSpringBootJpaTest {

    @BeforeEach
    public void setUp(){
        super.setUp();
    }

    @Test
    public void should_success_when_user_register(){
        UserRegisterUseCase userRegisterUseCase = newUserRegisterUseCase();
        UserRegisterInput input = userRegisterUseCase.newInput();
        input.setUsername(username);
        CqrsCommandOutput output = CqrsCommandPresenter.newInstance();
        userRegisterUseCase.execute(input, output);

        User user = userRepository.findById(username).get();
        assertEquals(username, user.getName());

    }

//    @Test
//    public void should_return_false_when_user_had_registered(){
//        registerUser(username);
//        CqrsCommandOutput output = registerUser(username);
//
//        assertNotNull(output.getId());
//        assertEquals(ExitCode.SUCCESS, output.getExitCode());
//        assertEquals("The username has already existed.", output.getMessage());
//    }
}
