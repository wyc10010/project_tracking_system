package ntut.csie.sslab.account.projects.command.adapter.restcontroller;

import ntut.csie.sslab.account.projects.command.entity.Role;
import ntut.csie.sslab.account.projects.command.usecase.addmember.AddProjectMemberInput;
import ntut.csie.sslab.account.projects.command.usecase.addmember.AddProjectMemberUseCase;
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
public class AddProjectMemberController {
    @Autowired
    AddProjectMemberUseCase addProjectMemberUseCase;

    @CrossOrigin(value = "http://localhost:3000")
    @PostMapping(path = "/projects/{projectId}/members", consumes = "application/json", produces = "application/json")
    public ResponseEntity<CqrsCommandViewModel> addProjectMember(@PathVariable("projectId") String projectId,
                                                                 @RequestBody String memberInfo){
        String username = "";
        String role = "";
        try {
            JSONObject projectJSON = new JSONObject(memberInfo);
            username = projectJSON.getString("username");
            role = projectJSON.getString("role");
        }catch (JSONException e){
            e.printStackTrace();
        }
        AddProjectMemberInput input = addProjectMemberUseCase.newInput();
        CqrsCommandPresenter presenter = CqrsCommandPresenter.newInstance();
        input.setProjectId(projectId);
        input.setUsername(username);
        input.setRole(role);
        addProjectMemberUseCase.execute(input, presenter);

        if(presenter.getExitCode() == ExitCode.SUCCESS){
            return new ResponseEntity<>(presenter.buildViewModel(), HttpStatus.OK);
        }
        return new ResponseEntity<>(presenter.buildViewModel(), HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
