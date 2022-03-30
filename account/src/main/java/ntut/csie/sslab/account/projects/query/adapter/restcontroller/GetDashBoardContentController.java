package ntut.csie.sslab.account.projects.query.adapter.restcontroller;


import ntut.csie.sslab.account.projects.query.adapter.presenter.getcontent.GetDashboardContentPresenter;
import ntut.csie.sslab.account.projects.query.adapter.presenter.getcontent.GetDashboardContentViewModel;
import ntut.csie.sslab.account.projects.query.usecase.getcontent.GetDashboardContentInput;
import ntut.csie.sslab.account.projects.query.usecase.getcontent.GetDashboardContentUseCase;
import ntut.csie.sslab.ddd.usecase.cqrs.ExitCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class GetDashBoardContentController {
    @Autowired
    private GetDashboardContentUseCase getDashboardContentUseCase;
    @CrossOrigin(value = "http://localhost:3000")
    @GetMapping(path = "/dashboard", produces = "application/json")
    public ResponseEntity<GetDashboardContentViewModel> getDashboardContent(@RequestParam(required = true, value = "username") String username){

        GetDashboardContentInput input = getDashboardContentUseCase.newInput();
        input.setUsername(username);
        GetDashboardContentPresenter presenter = new GetDashboardContentPresenter();
        getDashboardContentUseCase.execute(input, presenter);

        if(presenter.getExitCode() == ExitCode.SUCCESS){
            return new ResponseEntity<>(presenter.buildViewModel(), HttpStatus.OK);
        }
        return new ResponseEntity<>(presenter.buildViewModel(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
