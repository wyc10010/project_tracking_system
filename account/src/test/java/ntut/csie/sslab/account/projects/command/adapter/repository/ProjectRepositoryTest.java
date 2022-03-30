package ntut.csie.sslab.account.projects.command.adapter.repository;

import ntut.csie.sslab.account.AbstractSpringBootJpaTest;
import ntut.csie.sslab.account.projects.command.entity.Project;
import ntut.csie.sslab.account.projects.command.entity.Repository;
import ntut.csie.sslab.account.projects.command.entity.Role;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@Transactional
public class ProjectRepositoryTest extends AbstractSpringBootJpaTest {

    @Test
    public void should_success_when_save_project_with_project_member(){
        Project project = new Project(projectId, projectName, username);
        project.addProjectMember("anotherUser", Role.PROJECT_MANAGER);

        projectRepository.save(project);
        Project project_ = projectRepository.findById(projectId).get();

        assertEquals(projectId, project_.getId());
        assertEquals(projectName, project_.getName());
        assertEquals(2, project_.getProjectMembers().size());
        assertTrue(project_.getProjectMembers().containsKey(username));
        assertEquals(Role.PROJECT_MANAGER, project_.getProjectMembers().get(username));
    }

    @Test
    public void should_success_when_save_project(){
        Project project = new Project(projectId, projectName, username);
        project.addRepository("a", "A");
        project.addRepository("b", "B");
        project.addRepository("c", "C");
        project.addRepository("d", "D");
        projectRepository.save(project);
        Project project_ = projectRepository.findById(projectId).get();
        assertEquals(projectId, project_.getId());
        assertEquals(projectName, project_.getName());
        assertEquals(4, project_.getRepositories().size());
        assertEquals(1, project_.getProjectMembers().size());
    }

    @Test
    public void should_success_when_find_all_project_by_username(){
        Project project1 = new Project("1", "projectname1", username);
        project1.addRepository("a", "A");
        project1.addRepository("b", "B");
        projectRepository.save(project1);
        Project project2 = new Project("2", "projectname2", "anotherUser");
        project2.addRepository("c", "C");
        project2.addProjectMember(username, Role.PROJECT_USER);
        projectRepository.save(project2);
        Project project3 = new Project("3", "projectname3", "anotherUser");
        projectRepository.save(project3);

        List<Project> projects = projectRepository.findAllByUsername(username);
        assertEquals(2, projects.size());
    }
    @Test
    public void should_return_null_when_user_is_not_find(){
        Project project1 = new Project("1", "projectname1", username);
        project1.addRepository("a", "A");
        project1.addRepository("b", "B");
        projectRepository.save(project1);
        List<Project> projects = projectRepository.findAllByUsername("123");
        assertTrue(projects.isEmpty());
    }
}
