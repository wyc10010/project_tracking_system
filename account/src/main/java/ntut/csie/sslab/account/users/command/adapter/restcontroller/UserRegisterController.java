package ntut.csie.sslab.account.users.command.adapter.restcontroller;


import ntut.csie.sslab.account.users.command.usecase.UserRepository;
import ntut.csie.sslab.account.users.command.usecase.register.UserRegisterInput;
import ntut.csie.sslab.account.users.command.usecase.register.UserRegisterUseCase;
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
public class UserRegisterController {
    @Autowired
    private UserRegisterUseCase userRegisterUseCase;

    @CrossOrigin(value = "http://localhost:3000")
    @PostMapping(path = "/users/register", consumes = "application/json", produces = "application/json")
    public ResponseEntity<CqrsCommandViewModel> registerUser(@RequestBody String userInfo){

        String username = "";
        try {
            JSONObject userJSON = new JSONObject(userInfo);
            username = userJSON.getString("username");
        }catch (JSONException e){
            e.printStackTrace();
        }

        UserRegisterInput input = userRegisterUseCase.newInput();
        input.setUsername(username);
        CqrsCommandPresenter presenter = CqrsCommandPresenter.newInstance();
        userRegisterUseCase.execute(input, presenter);

        if(presenter.getExitCode() == ExitCode.SUCCESS){
            return new ResponseEntity<>(presenter.buildViewModel(), HttpStatus.OK);
        }

        return new ResponseEntity<>(presenter.buildViewModel(), HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
