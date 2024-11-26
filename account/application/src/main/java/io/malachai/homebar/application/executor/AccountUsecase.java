package io.malachai.homebar.application.executor;

import io.malachai.homebar.application.command.LoginCommand;
import io.malachai.homebar.application.command.RegisterCommand;
import io.malachai.homebar.application.command.RegisterConfirmCommand;
import io.malachai.homebar.application.command.UpdateCommand;
import io.malachai.homebar.application.transfer.TokenDto;

public interface AccountUsecase {

    void register(RegisterCommand command);

    void confirmRegister(RegisterConfirmCommand command);

    TokenDto login(LoginCommand command);

    void update(UpdateCommand command);
}
