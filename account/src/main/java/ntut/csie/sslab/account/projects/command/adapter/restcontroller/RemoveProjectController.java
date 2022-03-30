package ntut.csie.sslab.account.projects.command.adapter.restcontroller;


import ntut.csie.sslab.account.projects.command.usecase.removeproject.RemoveProjectInput;
import ntut.csie.sslab.account.projects.command.usecase.removeproject.RemoveProjectUseCase;
import ntut.csie.sslab.ddd.adapter.presenter.cqrs.CqrsCommandPresenter;
import ntut.csie.sslab.ddd.adapter.presenter.cqrs.CqrsCommandViewModel;
import ntut.csie.sslab.ddd.usecase.cqrs.ExitCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RemoveProjectController {
    @Autowired
    RemoveProjectUseCase removeProjectUseCase;

    @CrossOrigin(value = "http://localhost:3000")
    @DeleteMapping(path = "/projects/{projectId}", produces = "application/json")
    public ResponseEntity<CqrsCommandViewModel> removeProject(@PathVariable("projectId") String projectId){

        RemoveProjectInput input = removeProjectUseCase.newInput();
        CqrsCommandPresenter presenter = CqrsCommandPresenter.newInstance();
        input.setProjectId(projectId);
        removeProjectUseCase.execute(input, presenter);
        if(presenter.getExitCode()== ExitCode.SUCCESS){
            return new ResponseEntity<>(presenter.buildViewModel(), HttpStatus.OK);
        }
        return new ResponseEntity<>(presenter.buildViewModel(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
