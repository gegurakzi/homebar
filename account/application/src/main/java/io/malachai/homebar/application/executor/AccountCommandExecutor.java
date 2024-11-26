package io.malachai.homebar.application.executor;

import io.malachai.homebar.DomainEventPublisher;
import io.malachai.homebar.application.command.LoginCommand;
import io.malachai.homebar.application.command.RegisterCommand;
import io.malachai.homebar.application.command.RegisterConfirmCommand;
import io.malachai.homebar.application.command.UpdateCommand;
import io.malachai.homebar.application.transfer.TokenDto;
import io.malachai.homebar.domain.AuthenticatedUser;
import io.malachai.homebar.domain.LoginProcessor;
import io.malachai.homebar.domain.RegisterProcessor;
import io.malachai.homebar.domain.TokenGenerator;
import io.malachai.homebar.domain.TokenPair;
import io.malachai.homebar.domain.UpdateProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountCommandExecutor implements AccountUsecase {

    private final RegisterProcessor registerProcessor;
    private final LoginProcessor loginProcessor;
    private final UpdateProcessor updateProcessor;

    private final TokenGenerator tokenGenerator;

    private final DomainEventPublisher publisher;

    public void register(RegisterCommand command) {
        var account =
                registerProcessor.register(command.email(), command.password(), command.nickname());
        account.pollAllEvents().forEach(publisher::publishEvent);
    }

    public void confirmRegister(RegisterConfirmCommand command) {
        var account = registerProcessor.confirmRegister(command.email(), command.token());
        account.pollAllEvents().forEach(publisher::publishEvent);
    }

    public TokenDto login(LoginCommand command) {
        var account = loginProcessor.login(command.email(), command.password());
        TokenPair tokens =
                tokenGenerator.generate(
                        new AuthenticatedUser(
                                account.getEmail(),
                                account.getNickname(),
                                account.isEmailVerified(),
                                account.getRoles()));
        account.pollAllEvents().forEach(publisher::publishEvent);
        if (command.withRefreshToken()) {
            return new TokenDto(tokens.accessToken(), tokens.refreshToken());
        }
        return new TokenDto(tokens.accessToken(), null);
    }

    public void update(UpdateCommand command) {
        var account = updateProcessor.update(command.email(), command.nickname());
        account.pollAllEvents().forEach(publisher::publishEvent);
    }
}
