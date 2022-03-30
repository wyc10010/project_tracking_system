package ntut.csie.sslab.account.projects.query.usecase.get;

import ntut.csie.sslab.account.projects.query.usecase.getcontent.GetDashboardContentInput;
import ntut.csie.sslab.account.projects.query.usecase.getcontent.GetDashboardContentOutput;
import ntut.csie.sslab.ddd.usecase.cqrs.Query;

public interface GetProjectUseCase extends Query<GetProjectInput, GetProjectOutput> {
}
