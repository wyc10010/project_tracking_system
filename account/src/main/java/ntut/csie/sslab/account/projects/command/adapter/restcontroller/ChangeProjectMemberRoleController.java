package ntut.csie.sslab.account.projects.command.adapter.restcontroller;


import ntut.csie.sslab.account.projects.command.usecase.changememberrole.ChangeProjectMemberRoleInput;
import ntut.csie.sslab.account.projects.command.usecase.changememberrole.ChangeProjectMemberRoleUseCase;
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
public class ChangeProjectMemberRoleController {
    @Autowired
    private ChangeProjectMemberRoleUseCase changeProjectMemberRoleUseCase;
    @CrossOrigin(value = "http://localhost:3000")
    @PutMapping(path = "/projects/{projectId}/memberrole", consumes = "application/json", produces = "application/json")
    public ResponseEntity<CqrsCommandViewModel> changeProjectMemberRole(@PathVariable("projectId") String projectId,
                                                              @RequestBody String projectInfo){

        String username = "";
        String role = "";
        try {
            JSONObject projectJSON = new JSONObject(projectInfo);
            username = projectJSON.getString("username");
            role = projectJSON.getString("role");
        }catch (JSONException e){
            e.printStackTrace();
        }
        ChangeProjectMemberRoleInput input = changeProjectMemberRoleUseCase.newInput();
        CqrsCommandPresenter presenter = CqrsCommandPresenter.newInstance();
        input.setProjectId(projectId);
        input.setUsername(username);
        input.setRole(role);
        changeProjectMemberRoleUseCase.execute(input, presenter);

        if(presenter.getExitCode()== ExitCode.SUCCESS){
            return new ResponseEntity<>(presenter.buildViewModel(), HttpStatus.OK);
        }
        return new ResponseEntity<>(presenter.buildViewModel(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
