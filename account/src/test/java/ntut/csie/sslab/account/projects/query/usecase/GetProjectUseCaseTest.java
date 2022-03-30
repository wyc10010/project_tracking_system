package ntut.csie.sslab.account.projects.query.usecase;

import ntut.csie.sslab.account.AbstractSpringBootJpaTest;
import ntut.csie.sslab.account.projects.command.entity.Project;
import ntut.csie.sslab.account.projects.query.adapter.presenter.get.GetProjectPresenter;
import ntut.csie.sslab.account.projects.query.usecase.get.GetProjectInput;
import ntut.csie.sslab.account.projects.query.usecase.get.GetProjectOutput;
import ntut.csie.sslab.account.projects.query.usecase.get.GetProjectUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;

@Transactional
public class GetProjectUseCaseTest extends AbstractSpringBootJpaTest {
    @BeforeEach
    public void setUp(){
        super.setUp();
        makeSomeProjectDataInTheDB();
    }
    @Test
    public void should_success_when_get_project(){
        GetProjectUseCase useCase = newGetProjectUseCase();
        GetProjectInput input = useCase.newInput();
        input.setProjectId(project1Id);
        GetProjectOutput output = new GetProjectPresenter();
        useCase.execute(input, output);
        Project project = output.getProject();
    }

}
