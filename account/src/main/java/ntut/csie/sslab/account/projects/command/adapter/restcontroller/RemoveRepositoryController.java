package ntut.csie.sslab.account.projects.command.adapter.restcontroller;


import ntut.csie.sslab.account.projects.command.usecase.removerepository.RemoveRepositoryInput;
import ntut.csie.sslab.account.projects.command.usecase.removerepository.RemoveRepositoryUseCase;
import ntut.csie.sslab.ddd.adapter.presenter.cqrs.CqrsCommandPresenter;
import ntut.csie.sslab.ddd.adapter.presenter.cqrs.CqrsCommandViewModel;
import ntut.csie.sslab.ddd.usecase.cqrs.ExitCode;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RemoveRepositoryController {
    @Autowired
    private RemoveRepositoryUseCase removeRepositoryUseCase;
    @CrossOrigin(value = "http://localhost:3000")
    @PutMapping(path = "/projects/{projectId}/repositories/remove", produces = "application/json")
    public ResponseEntity<CqrsCommandViewModel> removeProject(@PathVariable("projectId") String projectId,
                                                              @RequestBody String repositoryInfo){
        String ownerName ="";
        String repositoryName = "";
        try {
            JSONObject projectJSON = new JSONObject(repositoryInfo);
            ownerName = projectJSON.getString("ownerName");
            repositoryName = projectJSON.getString("repositoryName");
        }catch (JSONException e){
            e.printStackTrace();
        }

        RemoveRepositoryInput input = removeRepositoryUseCase.newInput();
        CqrsCommandPresenter presenter = CqrsCommandPresenter.newInstance();
        input.setProjectId(projectId);
        input.setOwnerName(ownerName);
        input.setRepositoryName(repositoryName);
        removeRepositoryUseCase.execute(input, presenter);

        if(presenter.getExitCode()== ExitCode.SUCCESS){
            return new ResponseEntity<>(presenter.buildViewModel(), HttpStatus.OK);
        }
        return new ResponseEntity<>(presenter.buildViewModel(), HttpStatus.INTERNAL_SERVER_ERROR);

    }

}
