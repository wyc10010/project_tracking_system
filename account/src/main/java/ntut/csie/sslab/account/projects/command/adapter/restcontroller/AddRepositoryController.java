package ntut.csie.sslab.account.projects.command.adapter.restcontroller;

import ntut.csie.sslab.account.projects.command.usecase.addrepository.AddRepositoryInput;
import ntut.csie.sslab.account.projects.command.usecase.addrepository.AddRepositoryUseCase;
import ntut.csie.sslab.ddd.adapter.presenter.cqrs.CqrsCommandPresenter;
import ntut.csie.sslab.ddd.adapter.presenter.cqrs.CqrsCommandViewModel;
import ntut.csie.sslab.ddd.usecase.cqrs.CqrsCommandOutput;
import ntut.csie.sslab.ddd.usecase.cqrs.ExitCode;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AddRepositoryController {
    @Autowired
    private AddRepositoryUseCase addRepositoryUseCase;

    @CrossOrigin(value = "http://localhost:3000")
    @PostMapping(path = "/projects/{projectId}/repositories", consumes = "application/json", produces = "application/json")
    public ResponseEntity<CqrsCommandViewModel> addRepository(@PathVariable("projectId") String projectId,
                                                                @RequestBody String repositoryInfo){
        String ownerName = "";
        String repositoryName = "";
        try {
            JSONObject projectJSON = new JSONObject(repositoryInfo);
            ownerName = projectJSON.getString("ownerName");
            repositoryName = projectJSON.getString("repositoryName");
        }catch (JSONException e){
            e.printStackTrace();
        }
        AddRepositoryInput input = addRepositoryUseCase.newInput();
        input.setProjectId(projectId);
        input.setOwnerName(ownerName);
        input.setRepositoryName(repositoryName);
        CqrsCommandPresenter presenter = CqrsCommandPresenter.newInstance();
        addRepositoryUseCase.execute(input, presenter);

        if(presenter.getExitCode() == ExitCode.SUCCESS){
            return new ResponseEntity<>(presenter.buildViewModel(), HttpStatus.OK);
        }
        return new ResponseEntity<>(presenter.buildViewModel(), HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
