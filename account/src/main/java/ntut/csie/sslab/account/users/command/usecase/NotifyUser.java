package ntut.csie.sslab.account.users.command.usecase;

import com.google.common.eventbus.Subscribe;
import ntut.csie.sslab.account.projects.command.entity.event.ProjectAdded;
import ntut.csie.sslab.ddd.adapter.presenter.cqrs.CqrsCommandPresenter;
import ntut.csie.sslab.ddd.usecase.cqrs.CqrsCommandOutput;
import org.springframework.beans.factory.annotation.Autowired;

public class NotifyUser {
//    @Autowired
//    private CommitProjectUseCase commitProjectUseCase;

//    @Subscribe
//    public void whenProjectAdded(ProjectAdded event){
//        CommitProjectInput input = commitProjectUseCase.newInput();
//        CqrsCommandOutput output = CqrsCommandPresenter.newInstance();
//        input.setUserId(event.userId());
//        input.setProjectId(event.projectId());
//        commitProjectUseCase.execute(input, output);
//    }
}
