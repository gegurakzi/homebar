package io.malachai.homebar.presentation.request;

import io.malachai.homebar.application.command.LoginCommand;

public record LoginRequest(String email, String password) {

    public LoginCommand toCommand() {
        return new LoginCommand(this.email, this.password);
    }
}
