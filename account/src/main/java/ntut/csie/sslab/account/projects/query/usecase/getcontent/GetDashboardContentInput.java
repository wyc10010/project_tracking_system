package ntut.csie.sslab.account.projects.query.usecase.getcontent;

import ntut.csie.sslab.ddd.usecase.Input;

public interface GetDashboardContentInput extends Input {
    void setUsername(String username);
    String getUsername();
}
