package ntut.csie.sslab.account.users.command.entity;

import ntut.csie.sslab.account.AbstractSpringBootJpaTest;
import ntut.csie.sslab.account.users.command.entity.event.ProjectCommitted;
import ntut.csie.sslab.account.users.command.entity.event.UserRegistered;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static org.junit.Assert.assertEquals;


public class UserTest extends AbstractSpringBootJpaTest {

    private String firstProjectId = UUID.randomUUID().toString();
    private String secondProjectId = UUID.randomUUID().toString();

    @Test
    public void add_user_should_publish_user_added_domain_event(){
        User user = new User(username);

        assertEquals(1, user.getDomainEvents().size());
        UserRegistered event = (UserRegistered)user.getDomainEvents().get(0);
        assertEquals(username, event.username());
    }

}
