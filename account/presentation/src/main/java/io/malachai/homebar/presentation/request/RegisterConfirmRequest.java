package io.malachai.homebar.presentation.request;

import io.malachai.homebar.application.command.RegisterConfirmCommand;

public record RegisterConfirmRequest(String email, String token) {

    public RegisterConfirmCommand toCommand() {
        return new RegisterConfirmCommand(this.email, this.token);
    }
}
