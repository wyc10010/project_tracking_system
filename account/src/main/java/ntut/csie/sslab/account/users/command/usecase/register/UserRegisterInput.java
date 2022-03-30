package ntut.csie.sslab.account.users.command.usecase.register;

import ntut.csie.sslab.ddd.usecase.Input;

public interface UserRegisterInput extends Input {
    void setUsername(String username);
    String getUsername();
}
