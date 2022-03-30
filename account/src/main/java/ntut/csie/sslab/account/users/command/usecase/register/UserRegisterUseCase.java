package ntut.csie.sslab.account.users.command.usecase.register;

import ntut.csie.sslab.ddd.usecase.cqrs.Command;
import ntut.csie.sslab.ddd.usecase.cqrs.CqrsCommandOutput;

public interface UserRegisterUseCase extends Command<UserRegisterInput, CqrsCommandOutput> {
}
