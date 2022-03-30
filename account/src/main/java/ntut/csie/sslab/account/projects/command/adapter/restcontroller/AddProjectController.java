package ntut.csie.sslab.account.projects.command.adapter.restcontroller;

import ntut.csie.sslab.account.projects.command.usecase.add.AddProjectInput;
import ntut.csie.sslab.account.projects.command.usecase.add.AddProjectUseCase;
import ntut.csie.sslab.ddd.adapter.presenter.cqrs.CqrsCommandPresenter;
import ntut.csie.sslab.ddd.adapter.presenter.cqrs.CqrsCommandViewModel;
import ntut.csie.sslab.ddd.usecase.cqrs.ExitCode;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddProjectController {
    @Autowired
    private AddProjectUseCase addProjectUseCase;

    @CrossOrigin(value = "http://localhost:3000")
    @PostMapping(path = "/projects", consumes = "application/json", produces = "application/json")
    public ResponseEntity<CqrsCommandViewModel> addProject(@RequestBody String projectInfo){

        String username = "";
        String projectName = "";
        try {
            JSONObject projectJSON = new JSONObject(projectInfo);
            username = projectJSON.getString("username");
            projectName = projectJSON.getString("projectName");
        }catch (JSONException e){
            e.printStackTrace();
        }

        AddProjectInput input = addProjectUseCase.newInput();
        CqrsCommandPresenter presenter = CqrsCommandPresenter.newInstance();
        input.setUsername(username);
        input.setProjectName(projectName);
        addProjectUseCase.execute(input, presenter);

        if(presenter.getExitCode() == ExitCode.SUCCESS){
            return new ResponseEntity<>(presenter.buildViewModel(), HttpStatus.OK);
        }
        return new ResponseEntity<>(presenter.buildViewModel(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
