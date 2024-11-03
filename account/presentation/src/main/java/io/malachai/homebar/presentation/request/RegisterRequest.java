package io.malachai.homebar.presentation.request;

import io.malachai.homebar.application.command.RegisterCommand;

public record RegisterRequest(String email, String password, String nickname) {

    public RegisterCommand toCommand() {
        return new RegisterCommand(this.email, this.password, this.nickname);
    }
}
