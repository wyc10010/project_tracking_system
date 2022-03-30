package ntut.csie.sslab.account.users.adapter.repository;

import ntut.csie.sslab.account.AbstractSpringBootJpaTest;
import ntut.csie.sslab.account.users.command.entity.User;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class UserRepositoryTest extends AbstractSpringBootJpaTest {

    @Test
    public void should_success_when_save_user(){
        User user = new User(username);
        userRepository.save(user);
        User _user = userRepository.findById(username).get();
        assertEquals(username, _user.getName());
    }
}
