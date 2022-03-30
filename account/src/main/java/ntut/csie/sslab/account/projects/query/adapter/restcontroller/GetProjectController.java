package ntut.csie.sslab.account.projects.query.adapter.restcontroller;

import ntut.csie.sslab.account.projects.query.adapter.presenter.get.GetProjectPresenter;
import ntut.csie.sslab.account.projects.query.adapter.presenter.get.GetProjectViewModel;
import ntut.csie.sslab.account.projects.query.adapter.presenter.getcontent.GetDashboardContentViewModel;
import ntut.csie.sslab.account.projects.query.usecase.get.GetProjectInput;
import ntut.csie.sslab.account.projects.query.usecase.get.GetProjectOutput;
import ntut.csie.sslab.account.projects.query.usecase.get.GetProjectUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class GetProjectController {
    @Autowired
    private GetProjectUseCase getProjectUseCase;

    @CrossOrigin(value = "http://localhost:3000")
    @GetMapping(path = "/projects/{projectId}", produces = "application/json")
    public ResponseEntity<GetProjectViewModel> getDashboardContent(@PathVariable("projectId") String projectId) {
        GetProjectInput input = getProjectUseCase.newInput();
        input.setProjectId(projectId);
        GetProjectPresenter presenter = new GetProjectPresenter();
        getProjectUseCase.execute(input, presenter);
        return new ResponseEntity<>(presenter.buildViewModel(), HttpStatus.OK);
    }

}
