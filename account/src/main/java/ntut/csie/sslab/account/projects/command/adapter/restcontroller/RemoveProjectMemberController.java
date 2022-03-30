package ntut.csie.sslab.account.projects.command.adapter.restcontroller;


import ntut.csie.sslab.account.projects.command.usecase.removemember.RemoveProjectMemberInput;
import ntut.csie.sslab.account.projects.command.usecase.removemember.RemoveProjectMemberUseCase;
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
public class RemoveProjectMemberController {
    @Autowired
    RemoveProjectMemberUseCase removeProjectMemberUseCase;
    @CrossOrigin(value = "http://localhost:3000")
    @PutMapping(path = "/projects/{projectId}/memberremove", consumes = "application/json", produces = "application/json")
    public ResponseEntity<CqrsCommandViewModel> changeProjectMemberRole(@PathVariable("projectId") String projectId,
                                                                        @RequestBody String projectInfo){
        String username = "";
        try {
            JSONObject projectJSON = new JSONObject(projectInfo);
            username = projectJSON.getString("username");
        }catch (JSONException e){
            e.printStackTrace();
        }
        RemoveProjectMemberInput input = removeProjectMemberUseCase.newInput();
        CqrsCommandPresenter presenter = CqrsCommandPresenter.newInstance();
        input.setProjectId(projectId);
        input.setUsername(username);
        removeProjectMemberUseCase.execute(input, presenter);
        if(presenter.getExitCode()== ExitCode.SUCCESS){
            return new ResponseEntity<>(presenter.buildViewModel(), HttpStatus.OK);
        }
        return new ResponseEntity<>(presenter.buildViewModel(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
